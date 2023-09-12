package com.example.controller;

import com.example.controller.tool.Code;
import com.example.controller.tool.Result;
import com.example.controller.tool.Status;
import com.example.pojo.Company;
import com.example.service.CompanyService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@Controller
@RequestMapping("/companies")
@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping()
    public Result getBookById() {
        List<Company> companyAll = companyService.getCompanyAll();
        Integer status = companyAll != null ? Status.OPERATION_SUCCESS : Status.OPERATION_FAILURE;
        String msg = companyAll != null ? "查询成功" : "查询失败, 请重试";
        return new Result(status, companyAll, msg);
    }

    @PostMapping()
    public Result addCompany(@RequestBody Company company){
        boolean flag = companyService.addCompany(company);
        return new Result(flag ? Status.OPERATION_SUCCESS : Status.OPERATION_FAILURE, flag);
    }

    @GetMapping("/{comId}")
    public Result getCompanyById(@PathVariable Long comId){
        Company companyById = companyService.getCompanyById(comId);
        Integer status = companyById != null ? Status.OPERATION_SUCCESS : Status.OPERATION_FAILURE;
        String msg = companyById != null ? "查询成功" : "查询失败, 请重试";
        return new Result(status, companyById, msg);
    }

    @PutMapping("/{comId}")
    public Result updateCompanyById(@PathVariable Long comId, @RequestBody Company company){
        boolean flag = companyService.updateCompanyById(comId, company);
        return new Result(flag ? Status.OPERATION_SUCCESS : Status.OPERATION_FAILURE, flag);
    }

    @DeleteMapping("/{comId}")
    public Result deleteCompanyById(@PathVariable Long comId){
        boolean flag = companyService.deleteCompanyById(comId);
        return new Result(flag ? Status.OPERATION_SUCCESS : Status.OPERATION_FAILURE, flag);
    }
}
