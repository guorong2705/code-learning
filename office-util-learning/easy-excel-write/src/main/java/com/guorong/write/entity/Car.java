package com.guorong.write.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

/**
 * - @ColumnWidth(15) // 设置单元格宽度
 * - @HeadRowHeight(15) // 表头高度
 * - @ContentRowHeight(12) // 内容高度
 * @author guorong
 * @date 2021-05-15
 */
@HeadRowHeight(20)
@ContentRowHeight(12)
public class Car {

    @ColumnWidth(15)
    @ExcelProperty(value = "品牌")
    private String brand;

    @ColumnWidth(15)
    @ExcelProperty(value = "价格")
    private Double price;

    public Car() {}

    public Car(String brand, Double price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
