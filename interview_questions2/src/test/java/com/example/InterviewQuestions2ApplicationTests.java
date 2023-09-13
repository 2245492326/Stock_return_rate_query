package com.example;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.enums.SortingMethodEnum;
import com.example.mapper.FinalDataMapper;
import com.example.pojo.FinalData;
import com.example.service.FinalDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class InterviewQuestions2ApplicationTests {
    @Autowired
    private FinalDataMapper finalDataMapper;

    @Autowired
    private FinalDataService finalDataService;

    @Test
    void SelectPageTest(){
        Page<FinalData>page = new Page<>(2,4);//当前页和每页条数
        String sortField = "Net_Value_Per_Unit";
        String sortDirection = String.valueOf(SortingMethodEnum.ASC);
        // 设置排序规则
        List<OrderItem> orders = new ArrayList<>();
        if ("asc".equals(sortDirection)){
            // 按照字段 "fieldName1" 升序排序
            orders.add(OrderItem.asc(sortField));
        } else {
            // 按照字段 "fieldName2" 降序排序
            orders.add(OrderItem.desc(sortField));
        }
        page.setOrders(orders);

        finalDataMapper.selectPage(page, null);
        System.out.println("当前页数据: "+page.getRecords());
        System.out.println("当前页 页码："+page.getCurrent());
        System.out.println("每页显示的条数："+page.getSize());
        System.out.println("总记录数："+page.getTotal());
        System.out.println("总页数："+page.getPages());
        System.out.println("是否有上一页："+page.hasPrevious());
        System.out.println("是否有下一页："+page.hasNext());
    }

    @Test
    void PaginationSortTest(){
        Page<FinalData> page = finalDataService.PaginationSort(2, 4, "Net_Value_Per_Unit", SortingMethodEnum.ASC);
        System.out.println(page);
    }
}
