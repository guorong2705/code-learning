package com.guorong.ioc.v1;

import java.util.Random;

class Main {
    public static void main(String[] args) {
        TestCase myTestCase = new TestCase() {
            @Override
            public boolean doTest() {
                boolean randomBool = new Random().nextBoolean();
                System.out.println("do test randomBool --->>> " + randomBool);
                return randomBool;
            }
        };
        JunitApplication.registerCase(myTestCase);
        JunitApplication.run();
    }
}
