package com.guorong.singleton.lazy.double_check_lock;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

class Client {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(Singleton::getInstance).start();
        }
        TimeUnit.SECONDS.sleep(5);
    }
}


/**
 * 懒汉式单例模式：双重检查加锁
 */
class Singleton {

    /**
     * 这里加上 volatile 是为了禁止指令重排
     */
    private static volatile Singleton instance;
    /**
     * 私有化构造函数
     */
    private Singleton() {
        System.out.println("LazySingleton instance is created");
    }
    /**
     * 进行双重检查 (DCL)
     * @return
     */
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    // 创建实例
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}