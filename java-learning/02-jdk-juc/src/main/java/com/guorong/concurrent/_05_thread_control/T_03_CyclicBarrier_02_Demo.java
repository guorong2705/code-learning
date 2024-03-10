package com.guorong.concurrent._05_thread_control;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 循环栅栏: CyclicBarrier
 */
class T_03_CyclicBarrier_02_Demo {


    private static class Soldier implements Runnable {

        // 士兵名称
        private String soldierName;

        // 循环栅栏
        private CyclicBarrier cyclicBarrier;

        public Soldier(CyclicBarrier cyclicBarrier, String soldierName) {
            this.soldierName = soldierName;
            this.cyclicBarrier = cyclicBarrier;
        }

        // 报道
        private void gather() {
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                System.out.println(soldierName + "--报道...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 执行任务
        private void doWork() {
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                System.out.println(soldierName + "--完成任务...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 打扫战场
        private void clean() {
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                System.out.println(soldierName + "--打扫战场...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));

                // 士兵集合
                gather();
                cyclicBarrier.await();

                // 士兵执行任务
                doWork();
                cyclicBarrier.await();

                // 士兵打扫战场
                clean();
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


    // 每次到栅栏点,调用的任务
    private static class BarrierPointTask implements Runnable {

        // 标记阶段
        private int step = 0;

        @Override
        public void run() {
            step++;
            switch (step) {
                case 1:
                    System.out.println("====士兵集合完毕=====");
                    break;
                case 2:
                    System.out.println("====士兵任务完毕====");
                    break;
                case 3:
                    System.out.println("====士兵打扫完毕====");
                    break;

            }
        }
    }


    public static void main(String[] args) {
        int len = 10;
        // 线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(len);
        // 循环栅栏
        CyclicBarrier cyclicBarrier = new CyclicBarrier(len, new BarrierPointTask());

        for (int i = 0; i < len; i++) {
            String name = "士兵-" + i;
            threadPool.submit(new Soldier(cyclicBarrier, name));
        }

    }


}
