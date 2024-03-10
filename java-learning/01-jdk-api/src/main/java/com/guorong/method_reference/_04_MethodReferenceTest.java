package com.guorong.method_reference;

/**
 * 第四种：特定类型任意对象的实例化方法引用.
 * 第一点：接口方法的参数比引用方法的参数多一个.
 * 第二点：接口方法的第一个参数恰巧是调用引用方法的对象(其引用方法所在类或其父类的实例).
 */
class _04_MethodReferenceTest {

    @FunctionalInterface
    interface TwoArgs {
        void call(This aThis, String s, double d);
    }

    @FunctionalInterface
    interface ThreeArgs {
        void call(This aThis, String s, double d, int i);
    }

    static class This {
        public void two(String s, double d) {
            System.out.println("两个参数");
        }
        public void three(String s, double d, int i) {
            System.out.println("三个参数");
        }
    }

    public static void main(String[] args) {
        This aThis = new This();
        // 两个参数引用(未绑定的方法)
        TwoArgs twoArgs = This::two;
        twoArgs.call(aThis, "两个参数", 12.12);

        System.out.println("===============================================");

        This bThis = new This();
        // 三个参数引用(未绑定的方法)
        ThreeArgs threeArgs = This::three;
        threeArgs.call(bThis, "三个参数", 12.12, 20);
    }
}

