package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author cgs
 * @since 2023-09-14
 */
@TableName("FUND_NET_VAL")
public class FundNetVal implements Serializable {
    private Integer fundCode;

    private String fundShortName;

    private LocalDate endDate;

    private Double unitNetVal;

    public FundNetVal(Integer fundCode, String fundShortName, LocalDate endDate, Double unitNetVal) {
        this.fundCode = fundCode;
        this.fundShortName = fundShortName;
        this.endDate = endDate;
        this.unitNetVal = unitNetVal;
    }

    public FundNetVal() {
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getUnitNetVal() {
        return unitNetVal;
    }

    public void setUnitNetVal(Double unitNetVal) {
        this.unitNetVal = unitNetVal;
    }


    public void getDateStatistics(Double unitNetVal) {
        this.unitNetVal = unitNetVal;
    }

    @Override
    public String toString() {
        return "FundNetVal{" +
            "fundCode = " + fundCode +
            ", fundShortName = " + fundShortName +
            ", endDate = " + endDate +
            ", unitNetVal = " + unitNetVal +
        "}";
    }
}
