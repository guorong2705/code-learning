package com.guorong.concurrent._05_thread_control;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * (可以用在: 游戏中交换装备)
 * Exchanger 是 JDK 1.5 开始提供的一个用于两个工作线程之间交换数据的封装工具类，
 * 简单说就是一个线程在完成一定的事务后想与另一个线程交换数据，则第一个先拿出数据的线程会一直等待第二个线程，
 * 直到第二个线程拿着数据到来时才能彼此交换对应数据。其定义为 Exchanger<V> 泛型类型，其中 V 表示可交换的数据类型.
 */
class T_09_Exchanger_Demo {

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {

        int len = 10; // 循环次数

        new Thread(() -> {
            try {
                for (int i = 0; i < len; i++) {
                    String before = "T1..." + i;
                    String after = exchanger.exchange(before); // 此处阻塞, 等待交换数据,交换成功,继续执行
                    System.out.println(Thread.currentThread().getName() + "交换后数据: " + after);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程-T1").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < len; i++) {
                    String before = "T2..." + i;
                    TimeUnit.SECONDS.sleep(3); // 此处阻塞, 等待交换数据,交换成功,继续执行
                    String after = exchanger.exchange(before);
                    System.out.println(Thread.currentThread().getName() + "交换后数据: " + after);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程-T2").start();

    }


}
