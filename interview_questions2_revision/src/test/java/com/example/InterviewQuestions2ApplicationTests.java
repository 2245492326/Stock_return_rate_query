package com.example;

import com.example.entity.FinalData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class InterviewQuestions2ApplicationTests {

    @Autowired
    private List<FinalData> finalDataList;

    @Test
    void gitAllTest(){
        System.out.println(finalDataList);
    }

    @Test
    void allTest(){
        int pageNum = 2;
        int pageSize = 5;
        List<String> StringList = new ArrayList<>();
        StringList.add("1");
        StringList.add("2");
        StringList.add("3");

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


}
