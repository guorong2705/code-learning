package com.guorong.concurrent._04_xxx_lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁: 读-读共享锁, 写-写排他锁, 读-写排他锁.
 */
class T_03_Read_Write_Lock {

    private static class Counter {

        // 读写锁(独占锁)
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        // 读锁(共享锁)
        private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

        // 写锁(独占锁 )
        private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        private int value = 0;


        public void handleWrite(int value) {
            try {
                writeLock.lock();
                System.out.println(Thread.currentThread().getName() + "开始执行...");
                this.value =value;
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "执行结束...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }


        public void handleRead() {
            try {
                readLock.lock();
                System.out.println(Thread.currentThread().getName() + "开始执行...");
                System.out.println(Thread.currentThread().getName() + "读取数据 value = " + value);
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "执行结束...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }

    }


    public static void main(String[] args) {

        Counter counter = new Counter();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                counter.handleWrite(new Random().nextInt());
            }, "写线程--" + i).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                counter.handleRead();
            }, "读线程--" + i).start();
        }

    }


}
