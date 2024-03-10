package com.guorong.stream;

import lombok.Data;

/**
 * @author guorong
 * @date 2021-08-14
 */
@Data
class Apple {
    private String color;
    private double weight;

    public Apple() {}

    public Apple(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }
}
