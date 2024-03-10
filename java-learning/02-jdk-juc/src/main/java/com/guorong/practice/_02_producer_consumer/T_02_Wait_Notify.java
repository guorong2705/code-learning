package com.guorong.practice._02_producer_consumer;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用阻塞队列，来实现生产者-消费者模式
 */
class T_02_Wait_Notify {


    private static class Container<T> {

        private LinkedList<T> productList = new LinkedList<T>(); //

        private static final int MAX_SIZE = 5; // 最大容纳元素

        private static final int SLEEP_TIME = 2;

        // 生产产品
        public void put(T t) {
            try {
                Random random = new Random();
                while (true) {
                    synchronized (productList) {
                        while (productList.size() >= MAX_SIZE) {
                            productList.notifyAll(); // 唤醒等待队列中的线程
                            productList.wait(); // 当前线程等待, 并释放锁
                        }
                        productList.add(t);
                        TimeUnit.SECONDS.sleep(random.nextInt(SLEEP_TIME));
                        System.out.println("生产产品, 当前产品个数：" + productList.size());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 获取产品
        public void get() {
            Random random = new Random();
            try {
                while (true) {
                    synchronized (productList) {
                        while (productList.size() == 0) {
                            productList.notifyAll(); // 唤醒等待队列中的线程
                            productList.wait(); // 当前线程等待, 并释放锁
                        }
                        productList.remove();
                        TimeUnit.SECONDS.sleep(SLEEP_TIME);
                        System.out.println("消费产品, 当前产品个数：" + productList.size());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Container<String> container = new Container<String>();
        // 线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 提交生产者任务
        for (int i = 0; i < 3; i++) {
            threadPool.submit(() -> container.put("product"));
        }
        // 提交消费者任务
        for (int i = 0; i < 3; i++) {
            threadPool.submit(container::get);
        }

    }


}
