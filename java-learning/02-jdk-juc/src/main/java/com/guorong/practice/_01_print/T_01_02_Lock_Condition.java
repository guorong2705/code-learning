package com.guorong.practice._01_print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印，例如结果为：1A2B3C4D5E6F7G
 */
class T_01_02_Lock_Condition {

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    private static char[] numbers = "1234567".toCharArray();
    private static char[] letters = "ABCDEFG".toCharArray();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : letters) {
                    System.out.print(c);
                    condition.signal(); // 唤醒T2
                    condition.await(); // 暂停当前线程，释放锁
                }
                condition.signal();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "T1").start();


        new Thread(() -> {
            try {
                lock.lock();
                for (char c : numbers) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "T2").start();

    }
}
