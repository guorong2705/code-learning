package com.guorong.concurrent._09_thread_pool;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * newFixedThreadPool(nThreads): 创建一个核心线程个数和最大线程个数都为nThreads的线程池,
 * 并且阻塞队列(LinkedBlockingQueue)长度为 Integer.MAX_VALUE.
 * keepAliveTime=0说明只要线程个数比核心线程个数多并且当前空闲则回收.
 */
public class T_01_FixedThreadPool_Demo {

    // 固定线程数的线程池
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            threadPool.submit(() -> {
                try {
                    System.out.println(LocalTime.now().getSecond() + ", Thread ID:" + Thread.currentThread().getId());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }


}
