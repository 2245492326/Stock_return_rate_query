package com.example.pojo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author cgs
 * @since 2023-09-11
 */
public class Company implements Serializable {

    /**
     * 唯一编号
     */
    private Long orgUniCode;

    /**
     * 公司名称
     */
    private String orgChiName;

    /**
     * 行业
     */
    private String induSmaPar;

    private String orgDele;

    private Object regCap;

    /**
     * 创建日期
     */
    private LocalDate orgEstDate;

    public Company() {
    }

    public Long getOrgUniCode() {
        return orgUniCode;
    }

    public void setOrgUniCode(Long orgUniCode) {
        this.orgUniCode = orgUniCode;
    }

    public String getOrgChiName() {
        return orgChiName;
    }

    public void setOrgChiName(String orgChiName) {
        this.orgChiName = orgChiName;
    }

    public String getInduSmaPar() {
        return induSmaPar;
    }

    public void setInduSmaPar(String induSmaPar) {
        this.induSmaPar = induSmaPar;
    }

    public String getOrgDele() {
        return orgDele;
    }

    public void setOrgDele(String orgDele) {
        this.orgDele = orgDele;
    }

    public Object getRegCap() {
        return regCap;
    }

    public void setRegCap(Object regCap) {
        this.regCap = regCap;
    }

    public LocalDate getOrgEstDate() {
        return orgEstDate;
    }

    public void setOrgEstDate(LocalDate orgEstDate) {
        this.orgEstDate = orgEstDate;
    }

    @Override
    public String toString() {
        return "Company{" +
            "orgUniCode = " + orgUniCode +
            ", orgChiName = " + orgChiName +
            ", induSmaPar = " + induSmaPar +
            ", orgDele = " + orgDele +
            ", regCap = " + regCap +
            ", orgEstDate = " + orgEstDate +
        "}";
    }
}
