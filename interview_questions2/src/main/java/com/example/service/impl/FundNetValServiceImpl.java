package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.FinalData;
import com.example.entity.FundNetVal;
import com.example.entity.PagingParameters;
import com.example.mapper.FundNetValMapper;
import com.example.service.IFundNetValService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cgs
 * @since 2023-09-14
 */
@Service
public class FundNetValServiceImpl extends ServiceImpl<FundNetValMapper, FundNetVal> implements IFundNetValService {
    @Autowired
    private FundNetValMapper fundNetValMapper;
    public List<FinalData> PaginationSort(PagingParameters pagingParameters){
        class tool {
            /**
             * 带入公式计算
             * @param current 当前净值
             * @param nav 获取净值
             * @return 带%号,保留2为小数的计算结果
             */
            String calculate(double current, double nav){
                double number = (current - nav) * 100 / nav;
                //将计算结果保留两位小数,添加%,并返回
                return Double.parseDouble(new DecimalFormat("#.00").format(number))+"%";
            }

            /**
             * 执行SQL,并调用计算函数
             * @param finalData
             * @param queryWrapper 条件列表
             * @return 最终数值字符串
             */
            String executeSQL(FinalData finalData,QueryWrapper<FundNetVal> queryWrapper){
                //执行SQL
                List<Object> list = fundNetValMapper.selectObjs(queryWrapper);
                if (!list.isEmpty()){
                    //如果不为空则,调用calculate函数
                    return calculate(finalData.getNetValuePerUnit(),(double) list.get(0));
                }
                //没有查询结果,返回--表示空
                return "--";
            }

            /**
             * 调用executeSQL传入条件列表
             * @param finalData
             * @param interval 间隔时间
             * @return 最终数值字符串
             */
            String getUnitNetVal(FinalData finalData, String interval){
                //创建条件列表
                QueryWrapper<FundNetVal> queryWrapper = new QueryWrapper<>();
                //添加条件
                queryWrapper.select("UNIT_NET_VAL")
                        .eq("FUND_CODE", finalData.getFundCode())
                        .apply("END_DATE <= DATE_SUB('" + finalData.getDateStatistics() + "', INTERVAL "+ interval +")")
                        .orderByDesc("END_DATE")
                        .last("LIMIT 1");
                //调用
                return executeSQL(finalData,queryWrapper);
            }

            String getForNearlyOneYearUnitNetVal(FinalData finalData){
                QueryWrapper<FundNetVal> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("UNIT_NET_VAL")
                        .eq("FUND_CODE", finalData.getFundCode())
                        .apply("extract(YEAR FROM END_DATE) = extract(YEAR FROM '"+finalData.getDateStatistics()+"')")
                        .orderByAsc("END_DATE")
                        .last("LIMIT 1");
                return executeSQL(finalData,queryWrapper);
            }

            String getSinceItsEstablishment(FinalData finalData){
                QueryWrapper<FundNetVal> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("UNIT_NET_VAL")
                        .eq("FUND_CODE", finalData.getFundCode())
                        .orderByAsc("END_DATE")
                        .last("LIMIT 1");
                return executeSQL(finalData,queryWrapper);
            }
        }

        // 创建分页对象
        Page<FundNetVal> page = new Page<>(pagingParameters.getPageNum(),pagingParameters.getPageSize());//当前页和每页条数

        //编写查询条件
        QueryWrapper<FundNetVal> queryWrapper = Wrappers.<FundNetVal>query()
                .select("FUND_CODE", "FUND_SHORT_NAME", "MAX(END_DATE) AS END_DATE", "UNIT_NET_VAL")
                .groupBy("FUND_CODE");
        //查询数据
        Page<FundNetVal> fundNetValPage = fundNetValMapper.selectPage(page, queryWrapper);
        //获取当前页数据
        List<FundNetVal> fundNetValsList = fundNetValPage.getRecords();
        //定义FinalData集合
        List<FinalData> finalDataList = new ArrayList<>();

        //遍历fundNetValsList处理数据
        for (FundNetVal fundNetVal : fundNetValsList) {
            FinalData finalData = new FinalData(fundNetVal);
            //近一周
            finalData.setNearlyOneWeek(new tool().getUnitNetVal(finalData, "1 WEEK"));
            //近一月
            finalData.setNearlyOneMonth(new tool().getUnitNetVal(finalData, "1 MONTH"));
            //近三月
            finalData.setNearlyThreeMonth(new tool().getUnitNetVal(finalData, "3 MONTH"));
            //近一年
            finalData.setNearlyOneYear(new tool().getUnitNetVal(finalData, "1 YEAR"));
            //近两年
            finalData.setNearlyTwoYear(new tool().getUnitNetVal(finalData, "2 YEAR"));
            //近三年
            finalData.setNearlyThreeYear(new tool().getUnitNetVal(finalData, "3 YEAR"));
            //今年来
            finalData.setForNearlyOneYear(new tool().getForNearlyOneYearUnitNetVal(finalData));
            //成立以来
            finalData.setSinceItsEstablishment(new tool().getSinceItsEstablishment(finalData));
            finalDataList.add(finalData);
        }

        //排序
        // 动态指定getProperty方法
        Function<FinalData, Comparable> getProperty = finalData -> {
            try {
                String function;
                if (pagingParameters.getSortField().isEmpty()){
                    function = "netValuePerUnit";
                }else {
                    function = pagingParameters.getSortField();
                }
                // 使用反射根据属性名动态获取对应的get方法，并调用它获取属性值
                Method method = finalData.getClass().getMethod("get" + function);
                return (Comparable) method.invoke(finalData);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        };

        if (pagingParameters.getSortDirection().equals("asc")){
            //执行升序排序
            finalDataList.sort(Comparator.comparing(getProperty));
        }else {
            //执行降序排序
            finalDataList.sort(Comparator.comparing(getProperty).reversed());
        }
        return finalDataList;
    }

}
