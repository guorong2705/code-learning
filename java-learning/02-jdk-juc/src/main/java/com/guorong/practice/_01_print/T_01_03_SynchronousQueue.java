package com.guorong.practice._01_print;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 两个线程交替打印，结果为：1A2B3C4D5E6F7G
 */
class T_01_03_SynchronousQueue {

    private static BlockingQueue queueToT2 = new SynchronousQueue();
    private static BlockingQueue queueToT1 = new SynchronousQueue();

    private static char[] numbers = "1234567".toCharArray();
    private static char[] letters = "ABCDEFG".toCharArray();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                for (char num : numbers) {
                    queueToT2.put("ok");

                    System.out.print(num);

                    queueToT1.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "T1").start();


        new Thread(() -> {
            try {
                for (char letter : letters) {
                    queueToT2.take();

                    System.out.print(letter);

                    queueToT1.put("ok");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();


    }


}
