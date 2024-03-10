package com.guorong.practice._synchronized;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 锁对象必须是相同的，才能对多个线程进行同步
 * synchronized错误用法, 不可变的对象，对不可变对象进行操作，都会生成一个新的对象
 */
public class SynchronizedTest implements Runnable{

    private Integer count = 0;


    @Override
    public void run() {
        synchronized (count) {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            try {
                // Integer类型是个不可变的对象，对Integer对象进行操作，生成一个新的对象
                count ++;
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest runnable = new SynchronizedTest();
        new Thread(runnable).start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(runnable).start();
    }
}
