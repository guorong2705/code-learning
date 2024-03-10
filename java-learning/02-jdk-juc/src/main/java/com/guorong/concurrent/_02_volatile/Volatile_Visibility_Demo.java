package com.guorong.concurrent._02_volatile;

import java.util.concurrent.TimeUnit;

/**
 * 虚拟机在Server模式的可见性问题：原因是 JAVA 的内存模型导致的
 */
class Volatile_Visibility_Demo {

    /**
     * 加入 volatile 关键字可以保证可见性
     */
    private static /*volatile*/ boolean ready = true;

    private static class ReadThread extends Thread {
        @Override
        public void run() {
            while (ready) {

            }
            System.out.println("ReadThread 执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new ReadThread().start();

        TimeUnit.SECONDS.sleep(5);

        ready = false;
        System.out.println(Thread.currentThread().getName() + " 修改: read=false");

        TimeUnit.SECONDS.sleep(5);

        System.out.println(Thread.currentThread().getName() + "结束...");
    }

}
