package com.guorong.concurrent._05_thread_control;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量 Semaphore：控制同时执行的线程数量 (可以用于限流)
 */
class T_04_Semaphore_03_Demo {

    // 允许2个线程获取执行许可
    private static Semaphore semaphore = new Semaphore(2);

    // 任务
    private static Runnable runnable = () -> {
        try {
            // 获取许可
            semaphore.acquire();
            // 休眠
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + "正在运行...");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            // 释放许可证
            semaphore.release();
        }

    };


    public static void main(String[] args) {
        // 提交任务
        for (int i = 1; i < 20; i++) {
            new Thread(runnable, "线程--" + i).start();
        }
    }


}
