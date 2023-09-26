package com.example.controller;

import com.example.controller.tool.Result;
import com.example.controller.tool.Status;
import com.example.entity.FinalData;
import com.example.entity.PagingParameters;
import com.example.service.IFinalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cgs
 * @since 2023-09-14
 */
@Controller
@RestController
public class FinalDataController {
    @Autowired
    private IFinalDataService fundNetValService;

    @GetMapping("/fund")
    public Result paginationSort(@RequestBody PagingParameters pagingParameters){
        List<FinalData> finalDataList = fundNetValService.paginationSort(pagingParameters);
        Integer status = finalDataList != null ? Status.OPERATION_SUCCESS : Status.OPERATION_FAILURE;
        String msg = finalDataList != null ? "查询成功" : "查询失败, 请重试";
        return new Result(status, finalDataList, msg);
    }
}
