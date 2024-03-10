package com.guorong.singleton.hungry.static_field;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

class App {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            TimeUnit.MILLISECONDS.sleep(1000);
            new Thread(HungrySingleton::getInstance).start();
        }
    }
}

/**
 * 饿汉式单例模式: 在使用实例之前, 进行实例化
 */
class HungrySingleton {

    // 声明全局变量时，就创建对象
    private static final HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();

    private HungrySingleton(){
        System.out.println("HungrySingleton instance is created");
    }

    // 提供方法获取单例对象
    public static HungrySingleton getInstance(){
        System.out.println(LocalDateTime.now() + " - " + HUNGRY_SINGLETON);
        return HUNGRY_SINGLETON;
    }
}