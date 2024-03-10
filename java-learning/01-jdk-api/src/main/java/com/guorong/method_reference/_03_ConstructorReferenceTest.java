package com.guorong.method_reference;

/**
 * 第三种：构造器引用
 */
class _03_ConstructorReferenceTest {

    /**
     * 无参数
     */
    @FunctionalInterface
    interface NoArg {
        Dog make();
    }

    /**
     * 两个参数
     */
    @FunctionalInterface
    interface TwoArg {
        Dog make(String name, int age);
    }

    private static class Dog {
        public Dog() {
            System.out.println("无参数构造函数.....");
        }
        public Dog(String name, int age) {
            System.out.println("两个参数构造函数....");
        }
    }

    public static void main(String[] args) {
        // 无参数
        NoArg noArg = Dog::new;
        noArg.make();
        // 两个参数
        TwoArg twoArg = Dog::new;
        twoArg.make("张三", 23);
    }
}



