package com.guorong.concurrent._06_thread_local;

import java.util.Random;
import java.util.function.Supplier;

/**
 * 创建 ThreadLocal 的初始值
 */
class T_01_Create_ThreadLocal_Initial_Value {

    // 方式-01: 使用匿名内部类
    private static ThreadLocal<Integer> threadLocal01 = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return new Random().nextInt(100);
        }
    };


    // 方式-02: 使用 withInitial()方法
    private static ThreadLocal<Integer> threadLocal02 = ThreadLocal.withInitial(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(100);
            }
        });


    // 方式-03: 使用withInitial()方法, 配合 Lambda 表达式
    private static ThreadLocal<Integer> threadLocal03 = ThreadLocal.withInitial(() -> new Random().nextInt(100));


    public static void main(String[] args) {
        System.out.println(threadLocal01.get());


    }






}
