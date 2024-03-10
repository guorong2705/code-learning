package com.guorong.iterator._strategy_iterator;

import lombok.Data;

/**
 * @author guorong
 * @date 2021-08-25
 */
@Data
class MenuItem {
    /**
     * 菜单项名
     */
    private String name;

    /**
     * 是否为素食
     */
    private boolean vegetarian;

    /**
     * 价格
     */
    private double price;


    public MenuItem(String name, boolean vegetarian, double price) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.price = price;
    }

}
