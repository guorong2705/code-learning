package com.guorong.class_init_time;

import org.junit.jupiter.api.Test;

/**
 * 测试类加载时机
 */
public class ClassLoadTimeTest {

    @Test
    public void test01() {
        // 使用类字面量 不会导致类的加载
        Class<MyClass> clazz = MyClass.class;
    }

    @Test
    public void test02() {
        // 直接使用常量：不会导致类的加载
        String var = MyClass.VAR;
    }
}

class MyClass {

    public static final String VAR = "常量";

    static {
        System.out.println(String.format("%s ---- 初始化", MyClass.class.getSimpleName()));
    }
}
