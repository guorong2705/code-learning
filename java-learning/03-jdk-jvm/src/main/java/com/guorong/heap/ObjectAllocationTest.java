package com.guorong.heap;

import org.junit.jupiter.api.Test;

public class ObjectAllocationTest {

    private static final int _1MB = 1024 * 1024;

    /**
     * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void main(String[] args) {
        byte[] a1 = new byte[2 * _1MB];
        byte[] a2 = new byte[2 * _1MB];
        byte[] a3 = new byte[2 * _1MB];
        byte[] a4 = new byte[4 * _1MB];
    }
}
