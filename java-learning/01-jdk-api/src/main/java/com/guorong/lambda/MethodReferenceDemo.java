package com.guorong.lambda;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 方法引用主要有三类：
 * (1) 指向静态方法的方法引用（例如 Integer 的 parseInt 方法,写作Integer::parseInt
 *
 * (2) 指向任意类型实例方法的方法引用 (例如 String 的 length 方法,写作 String::length)
 *
 * (3) 指向现有对象实例方法的方法引用（假设你有一个局部变量 expensive-Transaction 用于存放 Transaction 类型的对象,
 *     它支持实例方法 getValue, 那么你就可以写 expensive-Transaction::getValue）
 * @author guorong
 * @create 2019-11-25
 */
public class MethodReferenceDemo {

    @FunctionalInterface
    private interface MyInterface {
        void println(String message);
    }

    static void staticMethod(String message) {
        System.out.println(message);
    }

    /**
     * (1) 指向静态方法的方法引用
     */
    @Test
    public void test01() {
        MyInterface myInterface = MethodReferenceDemo::staticMethod;
        myInterface.println("message ...");

        System.out.println("====================");
        Function<String, Integer> function = Integer::parseInt;
        Integer integer = function.apply("123");
    }


    @FunctionalInterface
    private interface Go {
        void println(GoClass goClass, String message);
    }

    private class GoClass {
        public void method(String message) {
            System.out.println(message);
        }
    }

    /**
     * (2) 指向任意类型实例方法的方法引用(也叫未绑定方法)
     */
    @Test
    public void test02() {
        Go go = GoClass::method;
        go.println(new GoClass(), "GoClass ....message");

        System.out.println("==================");
        Function<String, Integer> function1 = String::length;// "123".length()
        Function<String, Integer> function2 = (String s) -> s.length() ;
        Integer length = function1.apply("123");
    }


    /**
     * (3) 指向现有对象实例方法的方法引用
     */
    @Test
    public void test03() {
        PrintStream out = System.out;
        Consumer<Object> consumer = out::println;
        consumer.accept("123");
    }



}





