package com.guorong.function_interface;

import lombok.Data;

/**
 * @author guorong
 * @date 2021-08-14
 */
@Data
class Apple {
    /**
     * 颜色
     */
    private String color;
    /**
     * 重量
     */
    private Integer weight;
    /**
     * 产地、国家
     */
    private String country;

    public Apple() {}

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple(String color, Integer weight, String country) {
        this.color = color;
        this.weight = weight;
        this.country = country;
    }
}
