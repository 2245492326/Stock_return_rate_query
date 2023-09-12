package com.example.service.impl;

import com.example.mapper.CompanyMapper;
import com.example.pojo.Company;
import com.example.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> getCompanyAll() {
        List<Company> companyAll = companyMapper.getCompanyAll();
        return companyAll;
    }

    @Override
    public boolean addCompany(Company company) {
        int count = companyMapper.addCompany(company);
        return count > 0;
    }

    @Override
    public Company getCompanyById(Long comId) {
        Company companyById = companyMapper.getCompanyById(comId);
        return companyById;
    }

    @Override
    public boolean updateCompanyById(Long comId,Company company) {
        int count = companyMapper.updateCompanyById(comId,company);
        return count > 0;
    }

    @Override
    public boolean deleteCompanyById(Long comId) {
        int count = companyMapper.deleteCompanyById(comId);
        return count > 0;
    }
}
