package com.guorong.concurrent._04_xxx_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 独占锁 ReentrantLock: 同一个时刻只能有一个线程持有该锁(默认非公平锁)
 */
class T_01_ReentrantLock {

    // 独占锁
    private static ReentrantLock lock = new ReentrantLock();


    public void m1() {
        while (true) {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void m2() {
        while (true) {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        T_01_ReentrantLock r = new T_01_ReentrantLock();

        new Thread(r::m2, "线程02").start();
        new Thread(r::m1, "线程01").start();

    }


}
