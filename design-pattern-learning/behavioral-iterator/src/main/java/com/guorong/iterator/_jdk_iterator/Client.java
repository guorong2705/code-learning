package com.guorong.iterator._jdk_iterator;

/**
 * 使用jdk自带的迭代器接口
 * @author guorong
 */
class Client {

    public static void main(String[] args) {
        // 正餐菜单
        LunchMenu lunchMenu = new LunchMenu();
        // 咖啡菜单
        CoffeeMenu coffeeMenu = new CoffeeMenu();
        // 服务员
        Waiter waiter = new Waiter(lunchMenu, coffeeMenu);
        // 打印菜单
        waiter.printMenu();
    }
}
