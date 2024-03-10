package com.guorong.practice._01_print;

/**
 * 两个线程交替打印，结果为：1A2B3C4D5E6F7G
 */
class T_01_01_Wait_Notify {

    private static char[] numbers = "1234567".toCharArray();
    private static char[] letters = "ABCDEFG".toCharArray();

    private static Object obj = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                synchronized (obj) {
                    for (char c : numbers) {
                        System.out.print(c);
                        obj.notify(); // 唤醒obj锁对象线程等待队列中的一个线程
                        obj.wait(); // 当前线程等待，并释放锁
                    }
                    obj.notify(); // 保证T2线程正常执行完毕
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                synchronized (obj) {
                    for (char c : letters) {
                        System.out.print(c);
                        obj.notify(); // 唤醒obj锁对象线程等待队列中的一个线程
                        obj.wait(); // 当前线程等待，并释放锁
                    }
                    obj.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }


}
