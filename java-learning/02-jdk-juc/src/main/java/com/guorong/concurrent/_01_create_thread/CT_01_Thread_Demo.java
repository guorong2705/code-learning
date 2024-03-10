package com.guorong.concurrent._01_create_thread;

/**
 * 创建线程方式 Thread
 */
class CT_01_Thread_Demo {


    /**
     * 继承 Thread类
     */
    private static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            System.out.println("Thread Name: " + Thread.currentThread().getName());
        }
    }

    /**
     * 匿名内部类
     */
    private static Thread thread = new Thread("线程-02") {
        @Override
        public void run() {
            System.out.println("Thread Name：" + Thread.currentThread().getName());
        }
    };


    public static void main(String[] args) {
        new MyThread("线程-01").start();
        thread.start();
        throw new NullPointerException();
    }



}
