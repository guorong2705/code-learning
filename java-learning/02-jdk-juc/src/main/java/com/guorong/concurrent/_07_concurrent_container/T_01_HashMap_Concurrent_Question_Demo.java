package com.guorong.concurrent._07_concurrent_container;

import java.util.HashMap;
import java.util.UUID;

/**
 * 并发环境下不安全的 HashMap：
 *      多线程环境下，使用 HashMap 进行 put 操作会引起死循环，导致 CPU 利用率接近 100%，所以在并发情况下不能使用 HashMap。
 *      例如，执行以下代码会引起死循环，HashMap在并发执行put操作时，会引起死循环，是因为多线程会导致 HashMap
 *      的 Entry 链表形成环形结构，一旦形成环形结构，Entry的 next 节点永远不为空，就会产生死循环获取 Entry。
 */
public class T_01_HashMap_Concurrent_Question_Demo {

    public static void main(String[] args) throws InterruptedException {

        final HashMap<String, String> map = new HashMap<>(2);

        Thread t = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                new Thread(() -> {

                    map.put(UUID.randomUUID().toString(), "");

                }, "T" + i).start();

            }
            
        }, "T");

        t.start();
        t.join();


    }

}
