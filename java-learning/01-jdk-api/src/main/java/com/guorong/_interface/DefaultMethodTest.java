package com.guorong._interface;

import org.junit.jupiter.api.Test;

/**
 * 默认方法的演示： 实现了接口的类，也拥有了接口中的默认方法
 */
public class DefaultMethodTest {

    interface One {
        default void firstMethod() {
            System.out.println("first...Method");
        }
    }

    interface Two {
        default void secondMethod() {
            System.out.println("second...Method");
        }
    }

    interface Third {
        default void thirdMethod() {
            System.out.println("third...Method");
        }
    }

    class Mi implements One, Two, Third {}

    @Test
    public void test() {
        Mi mi = new Mi();
        mi.firstMethod();
        mi.secondMethod();
        mi.thirdMethod();
    }

}




