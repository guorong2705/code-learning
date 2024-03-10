package com.guorong.concurrent._04_xxx_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 ReentrantLock 的 tryLock() 方法尝试获得锁, 获得锁成功返回true,失败返回 false
 */
class T_02_ReentrantLock_TryLock {

    // 创建公平锁
    private ReentrantLock lock = new ReentrantLock();

    public void m1() {
        for (int i = 0; i < 100; i++) {
            boolean flag = false;
            try {
                // 尝试获得锁
                flag = lock.tryLock();
                TimeUnit.SECONDS.sleep(1);

                if (flag) {
                    System.out.println(Thread.currentThread().getName() + ": 获得锁-成功");
                }else {
                    System.out.println(Thread.currentThread().getName() + ": 获得锁-失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (flag) {
                    lock.unlock();
                }
            }
        }
    }

    public void m2() {
        for (int i = 0; i < 100; i++) {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(1);

                System.out.println(Thread.currentThread().getName() + ": 获得锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        T_02_ReentrantLock_TryLock rf = new T_02_ReentrantLock_TryLock();

        new Thread(rf::m1, "线程-01").start();
        new Thread(rf::m2, "线程-02").start();

    }


}
