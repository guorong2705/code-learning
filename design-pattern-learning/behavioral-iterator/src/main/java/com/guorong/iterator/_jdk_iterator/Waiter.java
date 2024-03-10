package com.guorong.iterator._jdk_iterator;


import java.util.Iterator;

/**
 * 服务员
 */
class Waiter {

    /**
     * 午餐菜单
     */
    private Iterable<MenuItem> lunchMenu;

    /**
     * 咖啡菜单
     */
    private Iterable<MenuItem> coffeeMenu;

    public Waiter(Iterable<MenuItem> lunchMenu, Iterable<MenuItem> coffeeMenu) {
        this.lunchMenu = lunchMenu;
        this.coffeeMenu = coffeeMenu;
    }

    /**
     * 打印出菜单项上的每一项
     */
    public void printMenu() {
        // 打印正餐菜单项
        printLunchMenu();
        // 打印咖啡菜单项
       printCoffeeMenu();
    }

    /**
     * 只打印午餐项
     */
    public void printLunchMenu() {
        System.out.println("---午餐菜单----");
        printMenu(lunchMenu.iterator());
    }

    public void printCoffeeMenu() {
        System.out.println("---咖啡菜单----");
        printMenu(coffeeMenu.iterator());
    }

    /**
     * 根据迭代器打印
     * @param iterator
     */
    private void printMenu(Iterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


}
