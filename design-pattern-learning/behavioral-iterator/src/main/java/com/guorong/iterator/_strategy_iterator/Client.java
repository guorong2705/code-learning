package com.guorong.iterator._strategy_iterator;

import java.util.Iterator;

/**
 * 策略迭代器测试
 * @author guorong
 * @date 2021-08-25
 */
class Client {

    public static void main(String[] args) {
        LunchMenu lunchMenu = new LunchMenu();
        Iterator<MenuItem> iterator = lunchMenu.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
