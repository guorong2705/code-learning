package com.guorong.freight;

public class Order {
    private String provinceCode;

    private String cityCode;

    private String countyCode;

    public Order(String provinceCode, String cityCode, String countyCode) {
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;
        this.countyCode = countyCode;
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
}
