package com.guorong.reference_type;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * 引用类型--软引用: 内存不足时, 才回收软引用
 */
public class SoftReferenceTests {


    /**
     * 设置堆内存容量: -Xms20m -Xmx20m
     */
    @SneakyThrows
    @Test
    public void test01() {
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]); // 软引用

        System.out.println(softReference.get());

        // 通知垃圾回收
        System.gc();

        TimeUnit.SECONDS.sleep(2);

        // 第一垃圾回收, 内存充足, 不回收软引用
        System.out.println(softReference.get());

        // 分配对象的时, 发现内存不足, 回收软引用
        byte[] ints = new byte[1024 * 1024 * 10];

        System.out.println(softReference.get());
    }

}
