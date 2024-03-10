package com.guorong.generic.generic_interface_2;

import java.util.function.Supplier;

/**
 * 斐波那契数列
 */
class Fibonacci implements Supplier<Integer> {

    private int count = 0;

    @Override
    public Integer get() {
        return fib(count++);
    }

    private int fib(int n) {
        return n < 2 ? 1 : fib(n - 2) + fib(n - 1);
    }
}
