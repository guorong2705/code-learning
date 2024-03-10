package com.guorong.thread;

import java.util.concurrent.*;

/**
 * 演示多线程
 *
 * @author guorong
 * @create 2019-12-31
 */
public class CallableDemo implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        // 创建任务
        CallableDemo task = new CallableDemo();
        // 提交任务
        Future<String> future = pool.submit(task);
        // 获取任务结束返回对象
        String result = future.get();
        System.out.println("hello world .......");

    }


    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("hello .... ");
        return "hello";
    }
}
