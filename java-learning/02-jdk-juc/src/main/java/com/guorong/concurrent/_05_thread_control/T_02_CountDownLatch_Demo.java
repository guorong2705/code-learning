package com.guorong.concurrent._05_thread_control;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatch: 倒计数器
 */
class T_02_CountDownLatch_Demo {

    // 倒计数器
    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    // 随机数生成器
    private static Random random = new Random(47);

    private static final int SLEEP_TIME = 6;

    // 原子操作类
    private static final AtomicInteger count = new AtomicInteger(0);

    // 任务
    private static Runnable runnable = () -> {
        try {
            // 模拟检查
            TimeUnit.SECONDS.sleep(random.nextInt(SLEEP_TIME));
            System.out.println(count.incrementAndGet() + "号任务完成.....");
            // 倒计数
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 提交线程
        for (int i = 0; i < 5; i++) {
            executorService.submit(runnable);
        }

        new Thread(() -> {
            try {
                countDownLatch.await();
                System.out.println("测试线程...启动了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 等待检查完毕
        countDownLatch.await();

        System.out.println("全都检查完毕...发射火箭");

        // 执行完线程池中的任务后，关闭线程池
        executorService.shutdown();
    }

}
