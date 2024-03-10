package com.guorong.concurrent._10_wait_notify;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guorong
 * @date 2020-11-12
 */
public class WN_01_Wait_Notify_Demo {

    private static List<String> list = new ArrayList<>();

    private static final int MAX_SIZE = 5;


    private static Runnable providerTask = () -> {
        synchronized (list) {
            while (list.size() >= MAX_SIZE) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add("product");
            list.notify();
        }
    };


    public static void main(String[] args) {




    }


}
