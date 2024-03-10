package com.guorong.concurrent._05_thread_control;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
class T_05_Lock_Condition_Demo {

    // 大炮类
    private static class Artillery {

        // 炮弹
        private LinkedList<String> bullets = new LinkedList<String>();

        // 大炮最大装弹量
        private static final int MAX_SIZE = 5;

        // 锁
        private Lock lock = new ReentrantLock();

        // 装弹条件
        private Condition inCon = lock.newCondition();

        // 发炮条件
        private Condition outCon = lock.newCondition();


        // 发炮
        public void outBullet() {
            for (;;) {
                try {
                    lock.lock(); // 加锁
                    while (bullets.size() == 0) {
                        inCon.signalAll(); // 唤醒装弹线程
                        outCon.await(); // 等待并且释放锁
                    }
                    bullets.removeLast(); // 发炮，移除最后一个元素
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ": 发炮... 当前炮弹数量: " + bullets.size());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }

        // 装弹
        public void inBullet() {
            for (;;) {
                try {
                    lock.lock(); // 加锁
                    while (bullets.size() >= MAX_SIZE) {
                        outCon.signalAll(); // 唤醒发炮线程
                        inCon.await(); // 当前线程等待,并释放锁
                    }
                    bullets.addFirst("炮弹"); // 装炮弹
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ": 装弹... 当前炮弹数量: " + bullets.size());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        // 大炮
        Artillery artillery = new Artillery();
        // 装弹任务
        for (int i = 0; i < 2; i++) {
            new Thread(artillery::inBullet, "装弹线程-" + i).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(artillery::outBullet, "发炮线程-" + i).start();
        }

    }




}
