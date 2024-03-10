package com.guorong._interface;

import org.junit.jupiter.api.Test;

/**
 * 在接口中使用静态方法
 */
public class StaticMethod {

    interface Operations {
        // 抽象方法
        void execute();
        // 静态方法
        static void show(String message) {
            System.out.println(message);
        }
        // 静态方法
        static void runOps(Operations... operations) {
            for (Operations ops : operations) {
                ops.execute();
            }
        }
    }

    class Bing implements Operations {

        @Override
        public void execute() {
            Operations.show("Bing.....");
        }
    }

    class Crack implements Operations {

        @Override
        public void execute() {
            Operations.show("Crack....");
        }
    }

    @Test
    public void test() {
        Operations.runOps(new Bing(), new Crack());
    }
}

