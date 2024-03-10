package com.guorong.generic.type_erasure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * 类型擦除:
 * 由于类型擦除移除了类型信息，对于无边界的泛型参数，你仅能调用Object中可用的方法。
 */
public class TypeErasureTests {

    /**
     * 泛型类型擦除比较测试
     */
    @Test
    public void test01() {
        Class<? extends ArrayList> c1 = new ArrayList<String>().getClass();
        Class<? extends ArrayList> c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);
    }

    @Test
    public void test02() {
        ClassAsFactory<ArrayList> factory1 = new ClassAsFactory<>(ArrayList.class);
        System.out.println(factory1.get());
        ClassAsFactory<HashMap> factory2 = new ClassAsFactory<>(HashMap.class);
        System.out.println(factory2.get());
    }
}

class ClassAsFactory<T> implements Supplier<T> {

    private final Class<T> type;

    public ClassAsFactory(Class<T> type) {
        this.type = type;
    }

    @Override
    public T get() {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

