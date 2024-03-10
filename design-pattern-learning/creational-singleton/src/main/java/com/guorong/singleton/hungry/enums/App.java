package com.guorong.singleton.hungry.enums;

import java.util.concurrent.TimeUnit;

class App {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            HungrySingletonEnum instance1 = HungrySingletonEnum.INSTANCE;
            TimeUnit.SECONDS.sleep(1);
            HungrySingletonEnum instance2 = HungrySingletonEnum.INSTANCE;
            // 判断两个实例的内存地址
            assert instance1 == instance2 : "不是同一个对象";
        }
    }

}

/**
 * 使用枚举实现单实例
 */
enum HungrySingletonEnum {
    INSTANCE;
}