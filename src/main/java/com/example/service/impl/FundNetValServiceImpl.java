package com.example.service.impl;

import static com.example.utils.PageUtil.getPage;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.FinalData;
import com.example.entity.FundNetVal;
import com.example.entity.PagingParameters;
import com.example.mapper.FundNetValMapper;
import com.example.service.IFundNetValService;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cgs
 * @since 2023-09-14
 */
@Service
public class FundNetValServiceImpl
    extends ServiceImpl<FundNetValMapper, FundNetVal>
    implements IFundNetValService {

  @Autowired private List<FinalData> finalDataList;

  @Override
  public List<FinalData> PaginationSort(PagingParameters pagingParameters) {
    String MethodName = "";
    // 排序
    if (pagingParameters.getSortField().isEmpty()) {
      MethodName = pagingParameters.getSortField();
    } else {
      MethodName = "NetValuePerUnit";
    }
    // 动态指定getProperty方法
    String finalMethodName = MethodName;
    Function<FinalData, Comparable> getProperty = finalData -> {
      try {
        // 使用反射根据属性名动态获取对应的get方法，并调用它获取属性值
        Method method = finalData.getClass().getMethod("get" + finalMethodName);
        return (Comparable)method.invoke(finalData);
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    };

    if (Objects.equals(pagingParameters.getSortDirection(), "asc")) {
      finalDataList.sort(Comparator.comparing(getProperty));
    } else {
      finalDataList.sort(Comparator.comparing(getProperty).reversed());
    }
    return getPage(finalDataList, pagingParameters.getPageNum(),
                   pagingParameters.getPageSize());
  }
}
