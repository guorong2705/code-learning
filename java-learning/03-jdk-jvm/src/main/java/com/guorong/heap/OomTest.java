package com.guorong.heap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class OomTest {

    /**
     * 测试 java 堆空间溢出：-Xms10m -Xmx10m
     */
    @Test
    void test(){
        printMemory();
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }

    private void printMemory() {
        // 虚拟机堆的内存大小
        long initialMemorySize = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        System.out.println("初始化堆大小： " + initialMemorySize + "M");
        // 虚拟机可以使用的最大堆内大小
        long maxMemorySize = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("最大的堆大小： " + maxMemorySize + "M");
    }

    static class OOMObject {
        private int _10mb = 1024 * 1024*10;
    }

}
