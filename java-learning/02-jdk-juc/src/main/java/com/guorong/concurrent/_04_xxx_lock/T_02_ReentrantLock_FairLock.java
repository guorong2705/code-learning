package com.guorong.concurrent._04_xxx_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 ReentrantLock 的公平锁
 */
class T_02_ReentrantLock_FairLock {

    // 公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    private static Runnable runnable = () -> {
        while (true) {
            try {
                // 加锁
                lock.lock();
                // 睡眠
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " 获得锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 解锁
                lock.unlock();
            }
        }
    };

    public static void main(String[] args) {
        new Thread(runnable, "thread-01").start();
        new Thread(runnable, "thread-02").start();
    }


}
