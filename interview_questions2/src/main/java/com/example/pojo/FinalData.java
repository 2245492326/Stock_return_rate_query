package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.annotations.One;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cgs
 * @since 2023-09-11
 */
@TableName(value ="Final_data")
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
    private Date DateStatistics;

    /**
     * 单位净值
     */
    private Double NetValuePerUnit;

    /**
     * 近一种
     */
    private String NearlyOneWeek;

    /**
     * 近一月
     */
    private String NearlyOneMonth;

    /**
     * 近三月
     */
    private String NearlyThreeMonth;

    /**
     * 近一年
     */
    private String NearlyOneYear;

    /**
     * 近两年
     */
    private String NearlyTwoYear;

    /**
     * 近三年
     */
    private String NearlyThreeYear;

    /**
     * 今年来
     */
    private String ForNearlyOneYear;

    /**
     * 成立来
     */
    private String SinceItsEstablishment;

    public FinalData() {
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

    public Date getDateStatistics() {
        return DateStatistics;
    }

    public void setDateStatistics(Date dateStatistics) {
        DateStatistics = dateStatistics;
    }

    public Double getNetValuePerUnit() {
        return NetValuePerUnit;
    }

    public void setNetValuePerUnit(Double netValuePerUnit) {
        NetValuePerUnit = netValuePerUnit;
    }

    public String getNearlyOneWeek() {
        return NearlyOneWeek;
    }

    public void setNearlyOneWeek(String nearlyOneWeek) {
        NearlyOneWeek = nearlyOneWeek;
    }

    public String getNearlyOneMonth() {
        return NearlyOneMonth;
    }

    public void setNearlyOneMonth(String nearlyOneMonth) {
        NearlyOneMonth = nearlyOneMonth;
    }

    public String getNearlyThreeMonth() {
        return NearlyThreeMonth;
    }

    public void setNearlyThreeMonth(String nearlyThreeMonth) {
        NearlyThreeMonth = nearlyThreeMonth;
    }

    public String getNearlyOneYear() {
        return NearlyOneYear;
    }

    public void setNearlyOneYear(String nearlyOneYear) {
        NearlyOneYear = nearlyOneYear;
    }

    public String getNearlyTwoYear() {
        return NearlyTwoYear;
    }

    public void setNearlyTwoYear(String nearlyTwoYear) {
        NearlyTwoYear = nearlyTwoYear;
    }

    public String getNearlyThreeYear() {
        return NearlyThreeYear;
    }

    public void setNearlyThreeYear(String nearlyThreeYear) {
        NearlyThreeYear = nearlyThreeYear;
    }

    public String getForNearlyOneYear() {
        return ForNearlyOneYear;
    }

    public void setForNearlyOneYear(String forNearlyOneYear) {
        ForNearlyOneYear = forNearlyOneYear;
    }

    public String getSinceItsEstablishment() {
        return SinceItsEstablishment;
    }

    public void setSinceItsEstablishment(String sinceItsEstablishment) {
        SinceItsEstablishment = sinceItsEstablishment;
    }

    @Override
    public String toString() {
        return "FinalData{" +
                "fundCode=" + fundCode +
                ", fundShortName='" + fundShortName + '\'' +
                ", DateStatistics=" + DateStatistics +
                ", NetValuePerUnit=" + NetValuePerUnit +
                ", NearlyOneWeek='" + NearlyOneWeek + '\'' +
                ", NearlyOneMonth='" + NearlyOneMonth + '\'' +
                ", NearlyThreeMonth='" + NearlyThreeMonth + '\'' +
                ", NearlyOneYear='" + NearlyOneYear + '\'' +
                ", NearlyTwoYear='" + NearlyTwoYear + '\'' +
                ", NearlyThreeYear='" + NearlyThreeYear + '\'' +
                ", ForNearlyOneYear='" + ForNearlyOneYear + '\'' +
                ", SinceItsEstablishment='" + SinceItsEstablishment + '\'' +
                '}';
    }
}
