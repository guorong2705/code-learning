package com.guorong.freight;

import java.math.BigDecimal;

public class RuleItem {
    private Long id;
    private Long ruleId;
    private String provinceCode;
    private String cityCode;
    private String countyCode;

    private String isDefault;

    public RuleItem(Long id, Long ruleId, String provinceCode, String cityCode, String countyCode, String isDefault) {
        this.id = id;
        this.ruleId = ruleId;
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;
        this.countyCode = countyCode;
        this.isDefault = isDefault;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    @Override
    public String toString() {
        return "RuleItem{" +
                "id=" + id +
                ", ruleId=" + ruleId +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", countyCode='" + countyCode + '\'' +
                ", isDefault='" + isDefault + '\'' +
                '}';
    }
}
