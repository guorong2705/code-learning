package com.guorong.class_load_subsystem;

import org.junit.jupiter.api.Test;

/**
 * 被动引用测试
 */
class PassiveCitationTest {

    /**
     * 01. 被动使用类字段演示一：通过子类引用父类的静态字段，不会导致子类初始化。
     * 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。
     */
    @Test
    void test01() {
        int value = SubClass.value;
    }


    /**
     * 02. 被动使用类字段演示二：通过数组定义来引用类，不会触发此类的初始化
     */
    @Test
    void test02() {
        SuperClass[] superClassArray = new SuperClass[10];
    }


    /**
     * 03. 被动使用类字段演示三：常量在编译阶段会存入调用类的常量池中，
     * 本质上没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化.
     */
    @Test
    void test03() {
        String constant = ConstClass.CONSTANT;
    }


    /**
     * 父类
     */
    static class SuperClass {
        static {
            System.out.println("SuperClass init...");
        }

        // 类变量：存放到方法区中
        public static int value = 123;
    }


    /**
     * 子类
     */
     static class SubClass extends SuperClass {
        static {
            System.out.println("SubClass init...");
        }
    }

    static class ConstClass {
        static {
            System.out.println("ConstClass init...");
        }

        // 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类
        public static final String CONSTANT = "hello world";
    }

}

