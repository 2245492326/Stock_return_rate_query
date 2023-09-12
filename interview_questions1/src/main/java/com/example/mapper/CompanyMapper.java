package com.example.mapper;

import com.example.pojo.Company;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyMapper {
    /**
     * 查询所以企业数据
     * @return 所以企业数据的集合
     */
    @Select("select orgUniCode,orgChiName,induSmaPar,orgDele,regCap,orgEstDate from company  order by orgEstDate desc")
    List<Company> getCompanyAll();

    /**
     * 添加企业数据
     * @param company 企业数据
     * @return 影响行数
     */
    int addCompany(Company company);

    /**
     * 根据id查询企业数据
     * @param comId 企业id
     * @return 企业数据
     */
    Company getCompanyById(Long comId);

    /**
     * 更新企业数据
     * @param company 企业数据
     * @return 影响行数
     */
    int updateCompanyById(Long comId, Company company);

    /**
     * 根据id删除企业数据
     * @param comId 企业id
     * @return 影响行数
     */
    @Delete("delete from company where orgUniCode = #{orgUniCode}")
    int deleteCompanyById(Long comId);
}
