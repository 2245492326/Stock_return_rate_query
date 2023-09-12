package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.enums.SortingMethodEnum;
import com.example.pojo.FinalData;


public interface FinalDataService extends IService<FinalData> {
    Page<FinalData> PaginationSort(Integer pageNum, Integer pageSize, String sortField, SortingMethodEnum sortingMethodEnum);
}
