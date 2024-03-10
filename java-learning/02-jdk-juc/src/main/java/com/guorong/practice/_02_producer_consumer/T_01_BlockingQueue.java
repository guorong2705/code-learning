package com.guorong.practice._02_producer_consumer;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用阻塞队列，来实现生产者-消费者模式
 */
class T_01_BlockingQueue {

    // 任务相关的数据
    private static class PCData {
        private final int intData;

        public PCData(int intData) {
            this.intData = intData;
        }

        public int getIntData() {
            return intData;
        }

        @Override
        public String toString() {
            return "data: " + intData;
        }
    }

    // 生产者任务
    private static class ProducerTask implements Runnable {

        private BlockingQueue<PCData> blockingQueue; // 内存缓冲区

        private volatile boolean isRunning = true; // 任务结束条件

        private static final int SLEEP_TIME = 3;

        private static AtomicInteger atomicInteger = new AtomicInteger(0); // 任务总数

        public ProducerTask(BlockingQueue blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            Random random = new Random();
            PCData data = null;
            System.out.println("生产者任务开始运行: " + Thread.currentThread().getId());
            try {
                while (isRunning) {

                    TimeUnit.SECONDS.sleep(random.nextInt(SLEEP_TIME)); // 睡眠

                    data = new PCData(atomicInteger.incrementAndGet()); // 数据

                    if (blockingQueue.offer(data, 2, TimeUnit.SECONDS)) {
                        System.out.println(data + "放入内存缓冲区成功....");
                    } else {
                        System.out.println(data + "放入内存缓冲区失败....");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 停止任务
        public void stop() {
            isRunning = false;
        }

    }


    // 消费者任务
    private static class ConsumerTask implements Runnable {

        private BlockingQueue<PCData> blockingQueue; // 内存缓冲区

        private static final int SLEEP_TIME = 3;

        public ConsumerTask(BlockingQueue<PCData> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }


        @Override
        public void run() {
            Random random = new Random();
            PCData data = null;

            System.out.println("生产者任务开始执行 :" + Thread.currentThread().getId());

            int computeResult = 0;

            try {
                while (true) {
                    data = blockingQueue.take(); // 获取内存缓冲区的数据, 此处会阻塞

                    if (data != null) {
                        computeResult = data.getIntData() * data.getIntData();
                        System.out.println(data.getIntData() + "的平方： " + computeResult);
                    }

                    TimeUnit.SECONDS.sleep(random.nextInt(SLEEP_TIME)); // 睡眠
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) {
        // 内存缓冲区
        BlockingQueue<PCData> blockingQueue = new ArrayBlockingQueue<>(5);
        // 线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 提交生产者任务
        for (int i = 0; i < 1; i++) {
            threadPool.submit(new ProducerTask(blockingQueue));
        }
        // 提交消费者任务
        for (int i = 0; i < 1; i++) {
            threadPool.submit(new ConsumerTask(blockingQueue));
        }


    }


}
