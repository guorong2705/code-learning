package com.guorong.concurrent._08_concurrent_queue.blocking;

import java.time.LocalTime;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列: 容量为 0 的阻塞队列
 */
class T_01_SynchronousQueue_Demo {

    // 容量为 0 的阻塞队列
    private static SynchronousQueue<String> queue = new SynchronousQueue<>();


    private static Runnable takeRunnable = () -> {
        while (true) {
            try {
                // 从队列中取出元素
                String element = queue.take();
                System.out.println("取出元素 = " + element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private static Runnable offerRunnable = () -> {
        for (; ; ) {
            try {
                String element = LocalTime.now().toString();
                boolean offer = queue.offer(element);
                if (offer) {
                    System.out.println("存入元素 = " + element);
                    TimeUnit.SECONDS.sleep(5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    public static void main(String[] args) {
        // 存入元素线程
        new Thread(offerRunnable).start();
        // 取出元素线程
        new Thread(takeRunnable).start();
    }


}
