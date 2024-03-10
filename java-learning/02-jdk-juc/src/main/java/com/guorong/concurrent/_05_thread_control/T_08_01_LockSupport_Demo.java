package com.guorong.concurrent._05_thread_control;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 是一个非常方便实用的阻塞工具
 */
class T_08_01_LockSupport_Demo {

    /**
     * 当t1线程调用 LockSupport.park()时, t1 线程将被挂起,
     * 然后main线程调用 LockSupport.unpark(t1), 这时挂起的t1线程将会被唤醒.
     */
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i + "...t1");
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

        TimeUnit.SECONDS.sleep(5);
        System.out.println("main--唤醒线程t1");
        LockSupport.unpark(t1); // 唤醒线程t1

    }


}
