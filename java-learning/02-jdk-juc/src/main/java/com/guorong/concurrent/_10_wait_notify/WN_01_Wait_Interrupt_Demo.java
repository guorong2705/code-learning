package com.guorong.concurrent._10_wait_notify;

import java.util.concurrent.TimeUnit;

/**
 * 当一个线程调用一个共享变量wait()方法时,该调用线程会被阻塞挂起,到发生下面几件事情之一才返回：
 * 1)线程调用了该共享对象notify()或者notifyAll()方法。
 * 2)其他线程调用了该线程interrupt()方法,该线程抛出interruptedException异常返回。
 *
 * 注意:如果调用wait()方法线程,没有事先获取该对象的监视器锁，则调用wait()方法时调用线程会抛出IllegalMonitorStateException异常。
 * @author guorong
 * @date 2020-11-12
 */
class WN_01_Wait_Interrupt_Demo {

    private static Object object = new Object();

    private static Runnable runnable = () -> {
        System.out.println(Thread.currentThread().getName() + "执行开始...");
        synchronized (object) {
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "执行结束...");
    };


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(runnable, "线程-01");
        thread.start();

        TimeUnit.SECONDS.sleep(5);
        thread.interrupt();

        System.out.println(Thread.currentThread().getName() + "执行结束...");
    }

}
