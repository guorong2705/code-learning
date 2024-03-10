package com.guorong.concurrent._05_thread_control;

import java.util.concurrent.*;

/**
 * 循环屏障: CyclicBarrier
 */
class T_03_CyclicBarrier_01_Demo {

    // 循环栅栏
    private static CyclicBarrier barrier = new CyclicBarrier(2, () -> {
        try {
            System.out.println("全部到达屏障点......");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    // 固定线程数量的线程池
    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);


    public static void main(String[] args) {
        threadPool.submit(() -> {
            try {
                String name = "任务-01";
                System.out.println(name + "开始执行...");

                TimeUnit.SECONDS.sleep(3);
                System.out.println(name + "到达屏障...");
                barrier.await();

                System.out.println(name + "任务执行完成...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        threadPool.submit(() -> {
            try {
                String name = "任务-02";
                System.out.println(name + "开始执行...");

                TimeUnit.SECONDS.sleep(7);
                System.out.println(name + "到达屏障...");
                barrier.await();

                System.out.println(name + "任务执行完成...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

    }



}
