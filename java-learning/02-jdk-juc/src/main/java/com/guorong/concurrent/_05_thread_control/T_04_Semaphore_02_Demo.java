package com.guorong.concurrent._05_thread_control;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *
 */
class T_04_Semaphore_02_Demo {

    // 信号量
    private static Semaphore semaphore = new Semaphore(2);

    // 线程池
    private static ExecutorService threadPool = Executors.newFixedThreadPool(6);

    private static class Task implements Runnable {

        private String taskName;

        private Random random = new Random();

        private static final int SLEEP_TIME = 10;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(SLEEP_TIME);
                System.out.println(taskName + "...任务完成");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        // 提交任务A
        threadPool.submit(new Task("任务A"));
        // 提交任务B
        threadPool.submit(new Task("任务B"));

        semaphore.acquire(2);
        System.out.println("任务A和B完成...");

        // 提交任务C
        threadPool.submit(new Task("任务C"));
        // 提交任务D
        threadPool.submit(new Task("任务D"));

        semaphore.acquire(10);
        System.out.println("任务C和D完成...");

        // 关闭线程池
        threadPool.shutdown();
    }




}
