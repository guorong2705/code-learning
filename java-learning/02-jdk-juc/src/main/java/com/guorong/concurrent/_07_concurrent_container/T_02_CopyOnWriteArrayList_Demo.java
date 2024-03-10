package com.guorong.concurrent._07_concurrent_container;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 */
public class T_02_CopyOnWriteArrayList_Demo {

    // 多线程安全容器: 写时复制策略
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();


    public static void main(String[] args) {

        Thread[] threads = new Thread[10000];

        for (int i = 0; i <threads.length; i++) {
            threads[i] = new Thread(() -> {
                list.add(UUID.randomUUID().toString());
            });
        }

        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.start();
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(list.size());


    }




}
