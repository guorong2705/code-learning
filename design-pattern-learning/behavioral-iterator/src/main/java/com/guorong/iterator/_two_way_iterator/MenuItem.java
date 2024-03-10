package com.guorong.iterator._two_way_iterator;

import lombok.Data;

/**
 * 菜单项
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
