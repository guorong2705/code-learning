package com.guorong.concurrent._03_atomic_xxx;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampReference 解决 ABA 问题
 */
class T_05_AtomicStampReference_Demo {

    private static AtomicStampedReference<Integer> atomic = new AtomicStampedReference<>(0, 0);


    public static void main(String[] args) {

        new Thread(() -> {
            try {
                Integer expectValue = atomic.getReference(); // 期望值
                int expectStamp = atomic.getStamp(); // 期望时间戳

                TimeUnit.SECONDS.sleep(5);

                if (atomic.compareAndSet(expectValue, expectStamp + 1, expectStamp, expectStamp + 1)) {
                    System.out.println(Thread.currentThread().getName() + " 更新成功...");
                }else {
                    System.out.println(Thread.currentThread().getName() + " 更新失败...");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "线程-01").start();


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);

                Integer expectValue = atomic.getReference();
                int expectStamp = atomic.getStamp();

                if (atomic.compareAndSet(expectValue, expectValue + 1, expectStamp, expectStamp + 1)) {
                    System.out.println(Thread.currentThread().getName() + " 更新成功...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程-02").start();

    }


}
