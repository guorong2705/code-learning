package com.guorong.heap;

import org.junit.jupiter.api.Test;

/**
 * 1. 默认堆空间的的大小：
 *    初始堆空间的大小：物理电脑的内存的 1/64
 *    最大堆空间的大小：物理电脑的内存的 1/4
 *
 * 2. 手动设置：-Xms600m -Xmx600m
 *    开发中建议将初始堆内存和最大堆内存设置为相同的值。
 *
 * 3. 查看设置的参数：
 *    方式 1：jps  /  jstat -gc 进程id
 *    方式 2：-XX:+PrintGCDetails
 *
 * @author guorong
 */
class HeapSpaceInitialTest {

    public static void main(String[] args) {
        // 返回java虚拟机堆的内存大小
        long initialMemorySize = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        // 存返回java虚拟机可以使用的最大堆内大小
        long maxMemorySize = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("初始化堆大小： " + initialMemorySize + "M");
        System.out.println("最大的堆大小： " + maxMemorySize + "M");
    }

}
