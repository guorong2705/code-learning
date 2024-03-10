package com.guorong.concurrent._11_thread_group;

import java.util.concurrent.TimeUnit;

class ThreadGroupTest {

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            // 线程组名称和线程名称
            String groupAndThreadName = Thread.currentThread().getThreadGroup().getName()
                    + "--" + Thread.currentThread().getName();
            while (true) {
                System.out.println(groupAndThreadName);
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        };
        // 线程组
        ThreadGroup threadGroup = new ThreadGroup("ThreadGroup1");
        // 线程
        Thread t1 = new Thread(threadGroup, task, "t1");
        Thread t2 = new Thread(threadGroup, task, "t2");
        Thread t3 = new Thread(threadGroup, task, "t3");
        // 启动线程
        t1.start();
        t2.start();
        t3.start();

        TimeUnit.SECONDS.sleep(5);
        threadGroup.list();
    }
}
