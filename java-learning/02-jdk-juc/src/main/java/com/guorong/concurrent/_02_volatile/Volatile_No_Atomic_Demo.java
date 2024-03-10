package com.guorong.concurrent._02_volatile;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile不能保证原子性
 * @author guorong
 * @date 2020-11-13
 */
class Volatile_No_Atomic_Demo {

    private volatile int count = 0;

    public int getCount() {
        return count;
    }

    public void method() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        Volatile_No_Atomic_Demo vd = new Volatile_No_Atomic_Demo();

        List<Thread> threads = new ArrayList<>();

        // 添加线程到集合
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(vd::method, "thread-"+i));
        }

        threads.forEach(thread -> thread.start());

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("count = " + vd.getCount());
    }



}
