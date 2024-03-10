package com.guorong.concurrent._01_create_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 创建线程方式 Callable
 *  和 Runnable相比，可以有返回值，可以抛出异常
 */
class CT_03_Callable_Demo {

    // 使用 Lambda 表达式
    private static Callable<Long> callable = () -> {
        long sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        TimeUnit.SECONDS.sleep(3);
        return sum;
    };


    private static class CallableTask implements Callable<Long> {

        @Override
        public Long call() throws Exception {
            long sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            TimeUnit.SECONDS.sleep(3);
            return sum;
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Long> futureTask = new FutureTask<>(callable);
        // 创建并启动线程
        new Thread(futureTask).start();
        // 阻塞当前线程, 等待获取返回值
        Long sum = futureTask.get();
        System.out.println("sum = " + sum);

    }

}
