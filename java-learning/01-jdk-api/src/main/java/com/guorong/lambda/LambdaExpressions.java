package com.guorong.lambda;

public class LambdaExpressions {

    @FunctionalInterface
    interface MySupplier {
        String get();
    }

    @FunctionalInterface
    interface MyConsumer {
        void accept(String s);
    }

    @FunctionalInterface
    interface MyBiFunction {
        String apply(String s1, String s2);
    }

    // 提供数据
    private MySupplier mySupplier = () -> "MySupplier";

    // 消费数据
    private MyConsumer myConsumer = s -> System.out.println(s);

    // 两个参数，并且有返回值
    private MyBiFunction myBiFunction = (s1,s2) -> s1.toLowerCase() + s2.toLowerCase();



}



