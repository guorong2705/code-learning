package com.guorong.concurrent._06_thread_local;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal是每个线程独有的, 设置到里面的值, 只能当前线程才能访问到,
 * Thread类中维护了一个 ThreadLocal.ThreadLocalMap threadLocals = null,
 */
class T_02_ThreadLocal_Demo {


    private static class MyRunnable implements Runnable {

        /**
         * 一个线程中的 ThreadLocalMap 可以关联多个 ThreadLocal 变量
         */
        private ThreadLocal<Integer> threadLocalNum = new ThreadLocal<>();
        private ThreadLocal<String> threadLocalString = new ThreadLocal<>();

        @Override
        public void run() {

            Integer num = threadLocalNum.get(); // 获取当前线程中的变量
            if (num == null) {
                threadLocalNum.set(new Random().nextInt(200));
            }

            String string = threadLocalString.get();
            if (string == null) {
                threadLocalString.set(new String("String variable"));
            }

            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}

            System.out.println(threadLocalNum.get());

            // 使用完毕ThreadLocal中的变量后,记得移除变量,防止内存泄漏
            threadLocalNum.remove();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable();

        Thread thread01 = new Thread(myRunnable);
        Thread thread02 = new Thread(myRunnable);

        thread01.start();
        thread02.start();

        thread01.join();
        thread02.join();

    }





}
