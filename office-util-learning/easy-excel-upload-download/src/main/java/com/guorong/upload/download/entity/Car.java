package com.guorong.upload.download.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * - @ColumnWidth(15) // 设置单元格宽度
 * @author guorong
 * @date 2021-05-15
 */
@Data
public class Car {

    @ColumnWidth(15)
    @ExcelProperty(value = "品牌", index = 0)
    private String brand;

    @ColumnWidth(15)
    @ExcelProperty(value = "价格", index = 1)
    private Double price;

    public Car() {}

    public Car(String brand, Double price) {
        this.brand = brand;
        this.price = price;
    }
}
