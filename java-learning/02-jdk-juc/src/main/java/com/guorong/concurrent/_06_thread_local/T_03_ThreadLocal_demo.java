package com.guorong.concurrent._06_thread_local;

/**
 * ThreadLocal是每个线程独有的，当前两个线程t1和t2同时对threadLocal中的数据进行操作，
 * 只会改变自己线程中的数据
 */
public class T_03_ThreadLocal_demo {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            threadLocal.set(1);
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "---" + threadLocal.get());
                // 将当前线程中的值 加1
                threadLocal.set(threadLocal.get() + 1);
            }
        }, "thread01");

        Thread t2 = new Thread(() -> {
            threadLocal.set(100);
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "--" + threadLocal.get());
                threadLocal.set(threadLocal.get() - 1);
            }

        }, "thread02");

        t1.start();
        t2.start();
    }
}
