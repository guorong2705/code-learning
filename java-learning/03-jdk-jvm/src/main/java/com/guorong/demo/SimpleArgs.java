package com.guorong.demo;

/**
 * 打印内存参数: -Xmx50m
 */
class SimpleArgs {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        long memory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("最大内存：" + memory);
    }
}
