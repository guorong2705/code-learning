package com.guorong.concurrent._05_thread_control;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 是一个非常方便实用的阻塞工具
 */
class T_08_02_LockSupport_Demo {


    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i + "....t1");
                if (i == 5) {
                    // 阻塞线程
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        // 如果t1线程之前没有调用park(), 则调用unpark()方法后,再调用park()方法,其会立刻返回.
        LockSupport.unpark(t1);
    }


}
