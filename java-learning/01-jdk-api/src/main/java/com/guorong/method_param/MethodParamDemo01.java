package com.guorong.method_param;

import lombok.Data;

/**
 * 方法参数:
 * Java 程序设计语言总是采用按值调用。
 * 也就是说， 方法得到的是所有参数值的一个拷贝，特别是，方法不能修改传递给它的任何参数变量的内容。
 */
public class MethodParamDemo01 {

    @Data
    private static class Person {
        private String name;
        private Integer age;
    }


    public static void main(String[] args) {
        int num = 12;
        changeNumber(num);
        System.out.println("num = " + num);

        String str = "change before";
        changeStr(str);
        System.out.println("str = " + str);

        Object object = new Object();
        System.out.println("change object before: " + object);
        changeReference(object);
        System.out.println("change object after:" + object);

        Person person = new Person();
        person.setName("change before");
        changeObject(person);
        System.out.println(person);

    }

    /**
     * 方法参数为 基本数字类型
     */
    public static void changeNumber(int numX) {
        numX = numX * 2;
    }

    /**
     * 方法参数为字符串
     */
    public static void changeStr(String strX) {
        strX = "change after";
    }

    /**
     * 方法参数为应用类型
     */
    public static void changeReference(Object objectX) {
        objectX = new Object();
    }


    public static void changeObject(Person person) {
        Person temp = person;
        temp.setName("change after");
    }

}
