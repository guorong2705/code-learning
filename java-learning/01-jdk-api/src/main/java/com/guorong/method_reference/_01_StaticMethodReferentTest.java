package com.guorong.method_reference;

/**
 * 第一种：静态方法引用
 */
class _01_StaticMethodReferentTest {

    // 函数接口
    @FunctionalInterface
    interface Callable {
        void call(String s);
    }

    private static class Play {
        public static void play(String s) {
            System.out.println("play..." + s);
        }
    }

    public static void main(String[] args) {
        // 静态方法引用
        Callable c1 = Play::play;
        c1.call("c1");
    }

}
