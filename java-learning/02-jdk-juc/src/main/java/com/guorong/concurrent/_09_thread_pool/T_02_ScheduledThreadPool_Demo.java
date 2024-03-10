package com.guorong.concurrent._09_thread_pool;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPool的实现原理, 其内部使用 DelayQueue 来存放具体任务.
 * 任务分为三种:
 *      1. 一次性执行任务执行完毕就结束.
 *      2. fixed-delay 任务保证同一个任务在多次执行之间间隔固定时间.
 *      3. fixed-rate 任务保证按照固定的频率执行.
 *
 */
public class T_02_ScheduledThreadPool_Demo {

    // 支持任务调度线程池
    private static ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);


    public static void main(String[] args) {
        // testScheduleWithFixedDelay();
        // testSchedule();
        testScheduleAtFixedRate();
    }


    /**
     * threadPool.schedule(runnable, 3, TimeUnit.SECONDS);
     * 提交任务后，延迟3秒后执行任务(只执行一次)
     */
    public static void testSchedule() {
        // 任务
        Runnable runnable = () -> {
            System.out.println(LocalTime.now().getSecond()+ ", Thread ID: " + Thread.currentThread().getId());
        };
        // 执行任务
        System.out.println(LocalTime.now().getSecond() + "========");
        for (int i = 1; i <= 5; i++) {
            threadPool.schedule(runnable, 3, TimeUnit.SECONDS);
        }
    }


    /**
     * threadPool.scheduleWithFixedDelay(runnable, 2, 3, TimeUnit.SECONDS);
     * 提交任务后，延迟2秒执行任务，后面每隔3秒中重复执行任务（周期性执行）
     */
    public static void testScheduleWithFixedDelay() {
        // 任务
        Runnable runnable = () -> {
            System.out.println(LocalTime.now().getSecond()+ ", Thread ID: " + Thread.currentThread().getId());
        };
        // 执行任务
        System.out.println(LocalTime.now().getSecond() + "========");
        for (int i = 0; i < 1; i++) {
            threadPool.scheduleWithFixedDelay(runnable, 2, 3, TimeUnit.SECONDS);
        }
    }


    public static void testScheduleAtFixedRate() {
        Runnable runnable = () -> {
            System.out.println(LocalTime.now().getSecond() + ", Thread ID: " + Thread.currentThread().getId());
        };

        System.out.println(LocalTime.now().getSecond() + "==========");
        for (int i = 0; i < 1; i++) {
            threadPool.scheduleAtFixedRate(runnable, 2, 3, TimeUnit.SECONDS);
        }

    }


}
