package com.guorong.class_load_subsystem;

import org.junit.jupiter.api.Test;

/**
 * 类初始化测试
 */
class ClassInitTest {

    /**
     * 01. 类进行初始化操作
     * 类初始化(调用<Clinit>方法)时候，会保证父类已经初始化了。
     */
    @Test
    void testInitClass() {
        SubClass subClass = new SubClass();
    }

    /**
     * 接口初始化时候，不需要保证父接口已经初始化.只有在真正使用父接口时候才会初始化。
     */
    @Test
    void testInitInterface() {
        System.out.println(SubInterface.super_value);
    }



    // 父类
    static class SuperClass {
        static {
            System.out.println("SuperClass Class Init");
        }
    }

    static class SubClass extends SuperClass {
        static {
            System.out.println("SubClass Class Init");
        }
    }

    static interface SuperInterface {
        int super_value = 12;
    }

    static interface SubInterface extends SuperInterface {
        int super_value = 13;
    }

}
