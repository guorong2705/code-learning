package com.guorong.read.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * @author guorong
 * @date 2021-05-15
 */
@Data
public class Car {

    @ExcelProperty(index = 0)
    private String brand;

    @ExcelProperty(index = 1)
    private Double price;

    public Car() {}

    public Car(String brand, Double price) {
        this.brand = brand;
        this.price = price;
    }
}
