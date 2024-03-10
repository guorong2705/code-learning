package com.guorong.iterator._jdk_iterator;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 咖啡菜单
 */
class CoffeeMenu implements Iterable<MenuItem>{

    /**
     * 存储咖啡菜单
     */
    private HashMap<String, MenuItem> itemHashMap = new HashMap<String,MenuItem>();

    public CoffeeMenu() {
        addMenuItem("拿铁咖啡", false, 20.12);
        addMenuItem("法式香草咖啡", true, 12.23);
        addMenuItem("红茶拿铁咖啡", true, 15.30);
        addMenuItem("芦荟柠檬汁", true, 13.30);
    }

    @Override
    public Iterator<MenuItem> iterator() {
        return itemHashMap.values().iterator();
    }

    /**
     * 添加咖啡菜单项
     * @param name
     * @param vegetarian
     * @param price
     */
    public void addMenuItem(String name , boolean vegetarian, double price) {
        itemHashMap.put(name, new MenuItem(name, vegetarian, price));
    }

}
