package com.guorong.practice._02_producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 实现消费者和生产者模式
 */
class T_03_Lock_Condition {


    private static class Container<T> {

        private LinkedList<T> products = new LinkedList<>();

        private Lock lock = new ReentrantLock();

        private Condition proCondition = lock.newCondition();

        private Condition conCondition = lock.newCondition();

        private static final int MAX_SIZE = 5;

        // 放入产品
        public void put(T t) {
            while (true){
                try {
                    lock.lock();

                    while (products.size() >= MAX_SIZE) {
                        conCondition.signalAll(); // 唤醒消费者线程
                        proCondition.await(); // 当前线程等待, 并释放锁
                    }

                    products.addFirst(t);
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "-生产产品, 当前容量=" + products.size());
                    conCondition.signalAll();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        // 获取产品
        public void get() {
            while (true) {
                try {
                    lock.lock();

                    while (products.size() == 0) {
                        proCondition.signalAll(); // 唤醒生产者线程
                        conCondition.await(); // 消费者线程等待
                    }

                    products.removeLast();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "-消费产品, 当前容量=" + products.size());

                    proCondition.signalAll();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    public static void main(String[] args) {

        Container<String> container = new Container<>();

        // 消费者线程
        for (int i = 1; i < 4; i++) {
            new Thread(container::get, "消费者线程-"+i).start();
        }

        // 生产者线程
        for (int i = 1; i < 4; i++) {
            new Thread(() -> container.put("product"), "生产者线程-" + i).start();
        }

    }






}
