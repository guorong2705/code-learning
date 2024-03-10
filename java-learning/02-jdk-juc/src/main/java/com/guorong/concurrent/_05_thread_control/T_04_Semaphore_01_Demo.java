package com.guorong.concurrent._05_thread_control;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 */
class T_04_Semaphore_01_Demo {

    // 信号量: 创建信号量,并指定内部计数器的初始值为"0"
    private static Semaphore semaphore = new Semaphore(0);

    // 线程池
    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);

    private static class MyRunnable implements Runnable {

        private String taskName;

        private Random random = new Random();

        private static final int SLEEP_TIME = 6;

        public MyRunnable(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(SLEEP_TIME));
                System.out.println(taskName + "...完成");
                semaphore.release(); // 相当于将 Semaphore 的信号量值增加 "1"
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        threadPool.submit(new MyRunnable("任务A"));
        threadPool.submit(new MyRunnable("任务B"));

        semaphore.acquire(2); // 获取两个信号量
        System.out.println("全部任务完成...");

        threadPool.shutdown();
    }

}
