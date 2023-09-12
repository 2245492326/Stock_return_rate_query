package com.example.service;

import com.example.pojo.Company;

import java.util.List;

public interface CompanyService {
    /**
     * 查询所以企业, 按时间排序降序
     * @return 查询的企业列表
     */
    List<Company> getCompanyAll();

    /**
     * 添加企业
     * @param company 企业数据
     * @return 添加是否成功
     */
    boolean addCompany(Company company);

    /**
     * 查询指定企业
     * @param comId 企业id
     * @return 查询的企业数据
     */
    Company getCompanyById(Long comId);

    /**
     * 更新指定企业
     * @param company 企业数据
     * @return 更新是否成功
     */
    boolean updateCompanyById(Long comId, Company company);

    /**
     * 删除指定企业
     * @param comId 企业id
     * @return 删除是否成功
     */
    boolean deleteCompanyById(Long comId);
}
