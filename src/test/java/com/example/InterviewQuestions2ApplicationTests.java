package com.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.FinalData;
import com.example.entity.FundNetVal;
import com.example.entity.PagingParameters;
import com.example.mapper.FundNetValMapper;
import com.example.service.IFundNetValService;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InterviewQuestions2ApplicationTests {
  @Autowired private FundNetValMapper fundNetValMapper;

  @Autowired private IFundNetValService fundNetValService;

  @Autowired private List<FinalData> finalDataList;

  @Test
  void allTest() {
    int pageNum = 2;
    int pageSize = 5;
    List<String> StringList = new ArrayList<>();
    StringList.add("1");
    StringList.add("2");
    StringList.add("3");
    StringList.add("4");
    StringList.add("5");
    StringList.add("6");
    StringList.add("7");
    StringList.add("8");
    StringList.add("9");

    // 计算起始索引和结束索引
    int startIndex = (pageNum - 1) * pageSize;
    int endIndex = Math.min(startIndex + pageSize, StringList.size());

    // 获取指定范围内的数据
    if (startIndex < endIndex) {
      System.out.println(StringList.subList(startIndex, endIndex));
    } else {
      System.out.println(new ArrayList<>());
    }
  }

  @Test
  void test() {
    int pageNum = 0;
    int pageSize = 5;
    String sortField = "NetValuePerUnit";
    String sortDirection = "desc";

    class test {
      String UnitNetValTest(FinalData finalData, String interval) {
        QueryWrapper<FundNetVal> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("UNIT_NET_VAL")
            .eq("FUND_CODE", finalData.getFundCode())
            .apply("END_DATE <= DATE_SUB('" + finalData.getDateStatistics() +
                   "', INTERVAL " + interval + ")")
            .orderByDesc("END_DATE")
            .last("LIMIT 1");
        List<Object> list = fundNetValMapper.selectObjs(queryWrapper);
        if (!list.isEmpty()) {
          return calculate(finalData.getNetValuePerUnit(), (double)list.get(0));
        }
        return "--";
      }
      String calculate(double current, double nav) {
        double number = (current - nav) * 100 / nav;
        return Double.parseDouble(new DecimalFormat("#.00").format(number)) +
            "%";
      }
    }

    // 编写查询条件
    QueryWrapper<FundNetVal> queryWrapper =
        Wrappers.<FundNetVal>query()
            .select("FUND_CODE", "FUND_SHORT_NAME", "MAX(END_DATE) AS END_DATE",
                    "UNIT_NET_VAL")
            .groupBy("FUND_CODE");
    List<FundNetVal> fundNetValsList =
        fundNetValMapper.selectList(queryWrapper);

    // 定义FinalData集合
    List<FinalData> finalDataList = new ArrayList<>();

    // 遍历fundNetValsList处理数据
    for (FundNetVal fundNetVal : fundNetValsList) {
      FinalData finalData = new FinalData(fundNetVal);
      // 近一周
      finalData.setNearlyOneWeek(
          new test().UnitNetValTest(finalData, "1 WEEK"));
      // 近一月
      finalData.setNearlyOneMonth(
          new test().UnitNetValTest(finalData, "1 MONTH"));
      // 近三月
      finalData.setNearlyThreeMonth(
          new test().UnitNetValTest(finalData, "3 MONTH"));
      // 近一年
      finalData.setNearlyOneYear(
          new test().UnitNetValTest(finalData, "1 YEAR"));
      // 近两年
      finalData.setNearlyTwoYear(
          new test().UnitNetValTest(finalData, "2 YEAR"));
      // 近三年
      finalData.setNearlyThreeYear(
          new test().UnitNetValTest(finalData, "3 YEAR"));
      // 今年来
      QueryWrapper<FundNetVal> forNearlyOneYearQueryWrapper =
          new QueryWrapper<>();
      forNearlyOneYearQueryWrapper.select("UNIT_NET_VAL")
          .eq("FUND_CODE", finalData.getFundCode())
          .apply("extract(YEAR FROM END_DATE) = extract(YEAR FROM '" +
                 finalData.getDateStatistics() + "')")
          .orderByAsc("END_DATE")
          .last("LIMIT 1");
      // 执行查询
      List<Object> forNearlyOneYearList =
          fundNetValMapper.selectObjs(forNearlyOneYearQueryWrapper);
      String forNearlyOneYear = "--";
      if (!forNearlyOneYearList.isEmpty()) {
        double current = finalData.getNetValuePerUnit();
        double nav = (double)forNearlyOneYearList.get(0);
        forNearlyOneYear = new test().calculate(current, nav);
      }
      finalData.setForNearlyOneYear(forNearlyOneYear);

      // 成立以来
      QueryWrapper<FundNetVal> sinceItsEstablishmentQueryWrapper =
          new QueryWrapper<>();
      sinceItsEstablishmentQueryWrapper.select("UNIT_NET_VAL")
          .eq("FUND_CODE", finalData.getFundCode())
          .orderByAsc("END_DATE")
          .last("LIMIT 1");
      List<Object> sinceItsEstablishmentList =
          fundNetValMapper.selectObjs(sinceItsEstablishmentQueryWrapper);
      String sinceItsEstablishment = "--";
      if (!sinceItsEstablishmentList.isEmpty()) {
        double current = finalData.getNetValuePerUnit();
        double nav = (double)sinceItsEstablishmentList.get(0);
        sinceItsEstablishment = new test().calculate(current, nav);
      }
      finalData.setSinceItsEstablishment(sinceItsEstablishment);
      finalDataList.add(finalData);
    }
  }

  @Test
  void test2() {
    QueryWrapper<FundNetVal> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("UNIT_NET_VAL")
        .eq("FUND_CODE", 927)
        .le("END_DATE", LocalDate.parse("2020-09-17").minusWeeks(1))
        .orderByDesc("END_DATE")
        .last("LIMIT 1");

    List<Object> list = fundNetValMapper.selectObjs(queryWrapper);
    double unitNetVal = (double)list.get(0);
    System.out.println(unitNetVal);
  }

  @Test
  void Test2() {
    int pageNum = 4;
    int pageSize = 6;
    String sortField = "NetValuePerUnit";
    String sortDirection = "desc";

    PagingParameters pagingParameters = new PagingParameters();
    pagingParameters.setPageNum(pageNum);
    pagingParameters.setPageSize(pageSize);
    pagingParameters.setSortField(sortField);
    pagingParameters.setSortDirection(sortDirection);
    List<FinalData> finalData =
        fundNetValService.PaginationSort(pagingParameters);
    for (FinalData finalDatum : finalData) {
      System.out.println(finalDatum);
    }
  }
}
