package com.guorong.reflection;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

class ClassMethodTests {

    @Test
    void testGetInterfaces() {
        Class<MyClass> clazz = MyClass.class;
        // 获取所有直接实现的接口
        Class<?>[] interfaces = clazz.getInterfaces();
        Stream.of(interfaces).map(Class::getName).forEach(System.out::println);
    }

    @Test
    void testGetSuperClass() {
        Class<MyClass> clazz = MyClass.class;
        // 获取直接继承的父类
        Class<? super MyClass> superclass = clazz.getSuperclass();
        System.out.println(superclass.getName());
    }

    @SneakyThrows
    @Test
    void testClassNewInstance() {
        Class<MyClass> clazz = MyClass.class;
        MyClass myClass = clazz.newInstance();
        System.out.println(myClass);
    }

    @Test
    void testGetMethod() {
        System.out.println("===========获取本类中声明的方法: ===============");
        Method[] methods = MyClass.class.getDeclaredMethods();
        Arrays.stream(methods)
                .map(Method::getName)
                .forEach(System.out::println);

        System.out.println("============获取类和父类中声明的方法===================");
        methods = MyClass.class.getMethods();
        Arrays.stream(methods)
                .map(Method::getName)
                .forEach(System.out::println);
    }

    @Test
    void testGetFields() {
        System.out.println("===========获取本类中声明的字段: ===============");
        Field[] fields = MyClass.class.getDeclaredFields();
        Arrays.stream(fields)
                .map(Field::getName)
                .forEach(System.out::println);

        System.out.println("============获取类和父类中声明的字段===================");
        fields = MyClass.class.getFields();
        Arrays.stream(fields)
                .map(Field::getName)
                .forEach(System.out::println);
    }
}

interface OneInterface {
}

interface TwoInterface {}

interface ThreeInterface extends TwoInterface{}

class SuperClass {
    private String superFiled = "SuperClass Filed";

    protected void superClassMethod() {}
}

class MyClass extends SuperClass implements OneInterface,ThreeInterface {
    private String instanceField;
    private static String classFiled;
    private static final String CONSTANT = "constant";
    public void method01() {}
    public void method02() {}
}