package com.guorong.demo;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GMain {

    private static final int _1MB = 1024 * 1024;

    /**
     * -Xms20m -Xmx20m
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        int i = 0;
        while (true) {
            i ++;
            TimeUnit.SECONDS.sleep(1);
            list.add(new byte[_1MB]);
            if (list.size() == 10) {
                list.clear();
            }
        }
    }
}
