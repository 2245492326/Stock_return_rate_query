package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.enums.SortingMethodEnum;
import com.example.mapper.FinalDataMapper;
import com.example.pojo.FinalData;
import com.example.service.FinalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FinalDataServiceImpl extends ServiceImpl<FinalDataMapper, FinalData> implements FinalDataService{
    @Autowired
    private FinalDataMapper finalDataMapper;
    public Page<FinalData> PaginationSort(Integer pageNum,Integer pageSize,String sortField,SortingMethodEnum sortingMethodEnum){
        Page<FinalData> page = new Page<>(pageNum,pageSize);//当前页和每页条数
        // 设置排序规则
        List<OrderItem> orders = new ArrayList<>();

        if (sortField == null || sortField.isEmpty()){
            sortField = "Net_Value_Per_Unit";
        }
        if (sortingMethodEnum == SortingMethodEnum.ASC){
            // 按照字段 "sortField" 升序排序
            orders.add(OrderItem.asc(sortField));
        } else {
            orders.add(OrderItem.desc(sortField));

        }

        page.setOrders(orders);
        finalDataMapper.selectPage(page, null);
        return page;
    }
}
