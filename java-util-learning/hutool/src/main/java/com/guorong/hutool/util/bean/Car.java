package com.guorong.hutool.util.bean;

import lombok.Data;

/**
 * @author guorong
 * @date 2021-05-24
 */
@Data
class Car {
    /**
     * 品牌
     */
    private String brand;
    /**
     * 价格
     */
    private Double price;

    public Car() {}

    public Car(String brand, Double price) {
        this.brand = brand;
        this.price = price;
    }
}
