package com.example;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.FinalData;
import com.example.entity.FundNetVal;
import com.example.entity.PagingParameters;
import com.example.mapper.FundNetValMapper;
import com.example.service.IFundNetValService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

@SpringBootTest
class InterviewQuestions2ApplicationTests {
    @Autowired
    private FundNetValMapper fundNetValMapper;

    @Autowired
    private IFundNetValService fundNetValService;

    @Test
    void allTest(){
        List<FundNetVal> FundNetValList = fundNetValMapper.selectList(null);

    }



    @Test
    void test(){
        int pageNum = 0;
        int pageSize = 5;
        String sortField = "NetValuePerUnit";
        String sortDirection = "desc";

        class test{
            String UnitNetValTest(FinalData finalData,String interval){
                QueryWrapper<FundNetVal> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("UNIT_NET_VAL")
                        .eq("FUND_CODE", finalData.getFundCode())
                        .apply("END_DATE <= DATE_SUB('" + finalData.getDateStatistics() + "', INTERVAL "+ interval +")")
                        .orderByDesc("END_DATE")
                        .last("LIMIT 1");
                List<Object> list = fundNetValMapper.selectObjs(queryWrapper);
                System.out.println(list);
                if (!list.isEmpty()){
                    return calculate(finalData.getNetValuePerUnit(),(double) list.get(0));
                }
                return "--";
            }
            String calculate(double current, double nav){
                double number = (current - nav) * 100 / nav;
                return Double.parseDouble(new DecimalFormat("#.00").format(number))+"%";
            }

        }

        // 创建分页对象
        Page<FundNetVal>page = new Page<>(pageNum,pageSize);//当前页和每页条数
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
            finalData.setNearlyOneWeek(new test().UnitNetValTest(finalData, "1 WEEK"));
            //近一月
            finalData.setNearlyOneMonth(new test().UnitNetValTest(finalData, "1 MONTH"));
            //近三月
            finalData.setNearlyThreeMonth(new test().UnitNetValTest(finalData, "3 MONTH"));
            //近一年
            finalData.setNearlyOneYear(new test().UnitNetValTest(finalData, "1 YEAR"));
            //近两年
            finalData.setNearlyTwoYear(new test().UnitNetValTest(finalData, "2 YEAR"));
            //近三年
            finalData.setNearlyThreeYear(new test().UnitNetValTest(finalData, "3 YEAR"));
            //今年来
            QueryWrapper<FundNetVal> forNearlyOneYearQueryWrapper = new QueryWrapper<>();
            forNearlyOneYearQueryWrapper.select("UNIT_NET_VAL")
                    .eq("FUND_CODE", finalData.getFundCode())
                    .apply("extract(YEAR FROM END_DATE) = extract(YEAR FROM '"+finalData.getDateStatistics()+"')")
                    .orderByAsc("END_DATE")
                    .last("LIMIT 1");
            // 执行查询
            List<Object> forNearlyOneYearList = fundNetValMapper.selectObjs(forNearlyOneYearQueryWrapper);
            String forNearlyOneYear = "--";
            if (!forNearlyOneYearList.isEmpty()){
                double current= finalData.getNetValuePerUnit();
                double nav = (double) forNearlyOneYearList.get(0);
                forNearlyOneYear = new test().calculate(current,nav);
            }
            finalData.setForNearlyOneYear(forNearlyOneYear);

            //成立以来
            QueryWrapper<FundNetVal> sinceItsEstablishmentQueryWrapper = new QueryWrapper<>();
            sinceItsEstablishmentQueryWrapper.select("UNIT_NET_VAL")
                    .eq("FUND_CODE", finalData.getFundCode())
                    .orderByAsc("END_DATE")
                    .last("LIMIT 1");
            List<Object> sinceItsEstablishmentList = fundNetValMapper.selectObjs(sinceItsEstablishmentQueryWrapper);
            String sinceItsEstablishment = "--";
            if (!sinceItsEstablishmentList.isEmpty()){
                double current= finalData.getNetValuePerUnit();
                double nav = (double) sinceItsEstablishmentList.get(0);
                sinceItsEstablishment = new test().calculate(current,nav);
            }
            finalData.setSinceItsEstablishment(sinceItsEstablishment);
            finalDataList.add(finalData);
        }

        //排序
        // 动态指定getProperty方法
        Function<FinalData, Comparable> getProperty = finalData -> {
            try {
                // 使用反射根据属性名动态获取对应的get方法，并调用它获取属性值
                Method method = finalData.getClass().getMethod("get" + sortField);
                return (Comparable) method.invoke(finalData);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        };
        if (sortDirection == "asc"){
            finalDataList.sort(Comparator.comparing(getProperty));
        }else {
            finalDataList.sort(Comparator.comparing(getProperty).reversed());
        }
        for (FinalData data : finalDataList) {
            System.out.println(data);
        }
    }

    @Test
    void test2(){
        QueryWrapper<FundNetVal> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("UNIT_NET_VAL")
                .eq("FUND_CODE", 927)
                .le("END_DATE", LocalDate.parse("2020-09-17").minusWeeks(1))
                .orderByDesc("END_DATE")
                .last("LIMIT 1");

        List<Object> list = fundNetValMapper.selectObjs(queryWrapper);
        double unitNetVal = (double) list.get(0);
        System.out.println(unitNetVal);
    }

    @Test
    void Test2(){
        int pageNum = 4;
        int pageSize = 6;
        String sortField = "NetValuePerUnit";
        String sortDirection = "desc";

        PagingParameters pagingParameters = new PagingParameters();
        pagingParameters.setPageNum(pageNum);
        pagingParameters.setPageSize(pageSize);
        pagingParameters.setSortField(sortField);
        pagingParameters.setSortDirection(sortDirection);
        List<FinalData> finalData = fundNetValService.PaginationSort(pagingParameters);
        for (FinalData finalDatum : finalData) {
            System.out.println(finalDatum);
        }
    }




}
