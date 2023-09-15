package com.example.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author cgs
 * @since 2023-09-11
 */
public class FinalData implements Serializable {
    /**
     * 基金代码
     */
    private Integer fundCode;

    /**
     * 基金简称
     */
    private String fundShortName;

    /**
     * 统计日期
     */
    private LocalDate dateStatistics;

    /**
     * 单位净值
     */
    private Double netValuePerUnit;

    /**
     * 近一种
     */
    private String nearlyOneWeek;

    /**
     * 近一月
     */
    private String nearlyOneMonth;

    /**
     * 近三月
     */
    private String nearlyThreeMonth;

    /**
     * 近一年
     */
    private String nearlyOneYear;

    /**
     * 近两年
     */
    private String nearlyTwoYear;

    /**
     * 近三年
     */
    private String nearlyThreeYear;

    /**
     * 今年来
     */
    private String forNearlyOneYear;

    /**
     * 成立来
     */
    private String sinceItsEstablishment;

    public FinalData() {
    }

    public FinalData(FundNetVal fundNetVal) {
        this.fundCode = fundNetVal.getFundCode();
        this.fundShortName = fundNetVal.getFundShortName();
        this.dateStatistics = fundNetVal.getEndDate();
        this.netValuePerUnit = fundNetVal.getUnitNetVal();
    }

    public Integer getFundCode() {
        return fundCode;
    }

    public void setFundCode(Integer fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundShortName() {
        return fundShortName;
    }

    public void setFundShortName(String fundShortName) {
        this.fundShortName = fundShortName;
    }

    public LocalDate getDateStatistics() {
        return dateStatistics;
    }

    public void setDateStatistics(LocalDate dateStatistics) {
        this.dateStatistics = dateStatistics;
    }

    public Double getNetValuePerUnit() {
        return netValuePerUnit;
    }

    public void setNetValuePerUnit(Double netValuePerUnit) {
        this.netValuePerUnit = netValuePerUnit;
    }

    public String getNearlyOneWeek() {
        return nearlyOneWeek;
    }

    public void setNearlyOneWeek(String nearlyOneWeek) {
        this.nearlyOneWeek = nearlyOneWeek;
    }

    public String getNearlyOneMonth() {
        return nearlyOneMonth;
    }

    public void setNearlyOneMonth(String nearlyOneMonth) {
        this.nearlyOneMonth = nearlyOneMonth;
    }

    public String getNearlyThreeMonth() {
        return nearlyThreeMonth;
    }

    public void setNearlyThreeMonth(String nearlyThreeMonth) {
        this.nearlyThreeMonth = nearlyThreeMonth;
    }

    public String getNearlyOneYear() {
        return nearlyOneYear;
    }

    public void setNearlyOneYear(String nearlyOneYear) {
        this.nearlyOneYear = nearlyOneYear;
    }

    public String getNearlyTwoYear() {
        return nearlyTwoYear;
    }

    public void setNearlyTwoYear(String nearlyTwoYear) {
        this.nearlyTwoYear = nearlyTwoYear;
    }

    public String getNearlyThreeYear() {
        return nearlyThreeYear;
    }

    public void setNearlyThreeYear(String nearlyThreeYear) {
        this.nearlyThreeYear = nearlyThreeYear;
    }

    public String getForNearlyOneYear() {
        return forNearlyOneYear;
    }

    public void setForNearlyOneYear(String forNearlyOneYear) {
        this.forNearlyOneYear = forNearlyOneYear;
    }

    public String getSinceItsEstablishment() {
        return sinceItsEstablishment;
    }

    public void setSinceItsEstablishment(String sinceItsEstablishment) {
        this.sinceItsEstablishment = sinceItsEstablishment;
    }

    @Override
    public String toString() {
        return "FinalData{" +
                "fundCode=" + fundCode +
                ", fundShortName='" + fundShortName + '\'' +
                ", DateStatistics=" + dateStatistics +
                ", NetValuePerUnit=" + netValuePerUnit +
                ", NearlyOneWeek='" + nearlyOneWeek + '\'' +
                ", NearlyOneMonth='" + nearlyOneMonth + '\'' +
                ", NearlyThreeMonth='" + nearlyThreeMonth + '\'' +
                ", NearlyOneYear='" + nearlyOneYear + '\'' +
                ", NearlyTwoYear='" + nearlyTwoYear + '\'' +
                ", NearlyThreeYear='" + nearlyThreeYear + '\'' +
                ", ForNearlyOneYear='" + forNearlyOneYear + '\'' +
                ", SinceItsEstablishment='" + sinceItsEstablishment + '\'' +
                '}';
    }
}