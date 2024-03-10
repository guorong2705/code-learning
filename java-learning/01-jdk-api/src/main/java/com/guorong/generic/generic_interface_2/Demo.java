package com.guorong.generic.generic_interface_2;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author guorong
 * @date 2021-08-14
 */
public class Demo {

    @Test
    public void test01() {
        Stream.generate(new Fibonacci())
                .limit(10)
                .map(i -> i + " ")
                .forEach(System.out::print);
    }
}
