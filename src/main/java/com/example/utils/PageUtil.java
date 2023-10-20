package com.example.utils;

import com.example.entity.FinalData;
import java.util.ArrayList;
import java.util.List;

public class PageUtil {
  public static List<FinalData> getPage(List<FinalData> finalDataList,
                                        int pageNum, int pageSize) {
    // 计算起始索引和结束索引
    int startIndex = (pageNum - 1) * pageSize;
    int endIndex = Math.min(startIndex + pageSize, finalDataList.size());

    // 获取指定范围内的数据
    if (startIndex < endIndex) {
      return finalDataList.subList(startIndex, endIndex);
    } else {
      return new ArrayList<>();
    }
  }
}
