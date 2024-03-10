package com.guorong.concurrent._09_thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 *
 */
class T_03_ForkJoinPool_Demo {

    // fork/join 线程池
    private static ForkJoinPool forkJoinPool = new ForkJoinPool();


    // 计算数列求和任务
    private static class CountTask extends RecursiveTask<Long> {

        // 进行fork操作的临界值
        private static final int THRESHOLD = 10000;

        // 数列的起始位置
        private long start;

        // 数列的最后位置
        private long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }


        @Override
        protected Long compute() {

            long sum = 0;

            if (end - start <= THRESHOLD) {
                for (int i = 0; i <= end; i++) {
                    sum += i;
                }


            } else {
                List<CountTask> subTasks = new ArrayList<>(); // 存储子任务

                long step = (end - start) / 100; // 进行步长计算

                long pos = start; // 子任务的数列的开始位置
                for (int i = 0; i < 100; i++) {

                    long lastOne = start + step; // 子任务数列的最后一个数

                    if (lastOne > end) { // 防止越界
                        lastOne = end;
                    }

                    CountTask subTask = new CountTask(pos, lastOne); // 创建子任务
                    subTask.fork(); // fork 子任务
                    subTasks.add(subTask); // 添加子任务到集合

                    pos = pos + step + 1; // 更新子任务开始计算位置
                }
                // 等待结果
                for (CountTask t : subTasks) {
                    Long subSum = t.join(); // 获取每次fork后计算的结果
                    sum += subSum;
                }

            }

            return sum;
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CountTask countTask = new CountTask(1, 10000);
        // 提交任务
        ForkJoinTask<Long> result = forkJoinPool.submit(countTask);

        // 获取结果
        Long sum = result.get();
        System.out.println("sum = " + sum);

    }


}
