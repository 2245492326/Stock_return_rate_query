package com.example;

import com.example.pojo.Company;
import com.example.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootTest
class InterviewQuestionsApplicationTests {
    @Autowired
    private CompanyService companyService;
    @Test
    void getCompanyAllTest() {
        List<Company> companyAll = companyService.getCompanyAll();
        System.out.println(companyAll);
    }

    @Test
    void addCompanyTest() throws ParseException {
        LocalDate localDate = LocalDate.of(2018, 11, 30);
        Company company = new Company(123456L, "饿了么", "外卖", "张旭豪", "123456", localDate);
        boolean b = companyService.addCompany(company);
        System.out.println(b);
    }

    @Test
    void getCompanyByIdTest(){
        Company companyById = companyService.getCompanyById(123456L);
        System.out.println(companyById);
    }

    @Test
    void updateCompanyByIdTest(){
        Company company = new Company(123456L, null, "外卖服务", null, "123456", null);
        boolean b = companyService.updateCompanyById(123456L, company);
        System.out.println(b);
    }

    @Test
    void deleteCompanyByIdTest(){
        boolean b = companyService.deleteCompanyById(123456L);
        System.out.println(b);
    }


}
