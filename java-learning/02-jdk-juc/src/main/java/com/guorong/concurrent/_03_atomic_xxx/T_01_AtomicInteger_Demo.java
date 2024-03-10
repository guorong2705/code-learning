package com.guorong.concurrent._03_atomic_xxx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用于测试AtomicInteger的原子操作
 */
class T_01_AtomicInteger_Demo {


    private static AtomicInteger count = new AtomicInteger();

    private static Runnable runnable = () -> {
        for (int i = 0; i < 10000; i++) {
            // 自增
            count.incrementAndGet();
        }
    };


    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(runnable));
        }
        // 启动线程
        threads.forEach(thread -> thread.start());

        // 等待线程结束
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(count.get());
    }


}
