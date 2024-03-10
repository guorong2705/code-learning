package com.guorong.concurrent._01_create_thread;

import java.util.concurrent.TimeUnit;

/**
 * 创建线程方式  Runnable:
 *   优点: 实现了任务代码和线程的分离
 *   缺点: 不能抛出异常, 不能有返回值
 */
class CT_02_Runnable_Demo {

    // 使用Lambda表达式实现
    private static Runnable runnable = () -> {
        try {
            System.out.println("Thread ID: " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };


    // 实现 Runnable 接口
    private static class RunnableTask implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Thread ID: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new Thread(runnable, "runnable-01").start();
        new Thread(runnable, "runnable-02").start();
    }




}
