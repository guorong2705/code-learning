package com.guorong.concurrent._09_thread_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 */
public class T_08_CustomThreadPool_Demo {


    // 自定义任务
    private static class MyTask implements Runnable {

        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println("正在执行：Thread ID: " + Thread.currentThread().getId() + ", Task Name: " + name);

                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // 自定义线程池
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(20)) {

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("准备执行：" + ((MyTask) r).name);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            System.out.println("执行完成：" + ((MyTask) r).name);
        }

        @Override
        protected void terminated() {
            System.out.println("线程池退出");
        }
    };



    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            threadPool.execute(new MyTask("Task " + i));
        }


        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();

    }


}
