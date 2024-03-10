package com.guorong.concurrent._03_atomic_xxx;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用 AtomicLong 统计数组中 0 的个数
 */
class T_02_AtomicLong_Demo {

    private static AtomicLong atomic = new AtomicLong(0);

    private static int[] arrayOne = {1, 0, 12, 45, 0, 3, 0, 0};

    private static int[] arrayTwo = {23, 45, 0, 0, 12, 0};

    public static void main(String[] args) throws InterruptedException {

        // 统计arrayOne数组中 0 的个数
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < arrayOne.length; i++) {

                if (arrayOne[i] == 0) {
                    atomic.incrementAndGet();
                }
            }
        });

        // 统计arrayTwo数组中 0 的个数
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < arrayTwo.length; i++) {

                if (arrayTwo[i] == 0) {
                    atomic.incrementAndGet();
                }
            }
        });

        // 启动线程
        t1.start();
        t2.start();

        // 等待线程完成
        t1.join();
        t2.join();

        System.out.println("两个数组中-0-的个数: " + atomic.get());

    }



}
