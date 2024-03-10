package com.guorong.function_interface;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * @author guorong
 * @date 2021-09-05
 */
public class AndThenTest {

    private Consumer<String> oneConsumer = (String s) -> {
        System.out.println("One ..." + s);
    };

    private Consumer<String> twoConsumer = (String s) -> {
        System.out.println("Two ..." + s);
    };

    // 关联函数接口，在执行 oneConsumer 后再执行 twoConsumer
    public Consumer<String> MyAndThen() {
        return oneConsumer.andThen(twoConsumer);
    }

    @Test
    public void test() {
        Consumer<String> stringConsumer = MyAndThen();
        stringConsumer.accept("aa");
    }
}
