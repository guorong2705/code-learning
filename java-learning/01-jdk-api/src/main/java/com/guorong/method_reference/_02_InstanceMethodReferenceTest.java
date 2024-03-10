package com.guorong.method_reference;

/**
 * 第二种：特定对象的实例化方法引用
 */
class _02_InstanceMethodReferenceTest {

    // 函数接口
    @FunctionalInterface
    interface Callable {
        void call(String s);
    }

    private static class Show {
        public void show(String s) {
            System.out.println("show..." + s);
        }
    }

    public static void main(String[] args) {
        Show show = new Show();
        Callable c2 = show::show;
        c2.call("c2");
    }

}




