package com.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.controller.tool.Result;
import com.example.controller.tool.Status;
import com.example.enums.SortingMethodEnum;
import com.example.pojo.FinalData;
import com.example.pojo.PagingParameters;
import com.example.service.FinalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
public class FinalDataController {
    @Autowired
    private FinalDataService finalDataService;

    @GetMapping("/fund")
    public Result PaginationSort(@RequestBody PagingParameters pagingParameters){
        SortingMethodEnum sortingMethodEnum;
        if (pagingParameters.getSortDirection().equals(String.valueOf(SortingMethodEnum.ASC))){
            sortingMethodEnum = SortingMethodEnum.ASC;
        }else {
            sortingMethodEnum = SortingMethodEnum.DESC;
        }
        Page<FinalData> page = finalDataService.PaginationSort(pagingParameters.getPageNum(), pagingParameters.getPageSize(), pagingParameters.getSortField(), sortingMethodEnum);

        Integer status = page != null ? Status.OPERATION_SUCCESS : Status.OPERATION_FAILURE;
        String msg = page != null ? "查询成功" : "查询失败, 请重试";
        return new Result(status, page, msg);
    }
}
