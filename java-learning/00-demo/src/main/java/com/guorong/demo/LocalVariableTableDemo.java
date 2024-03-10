package com.guorong.demo;

public class LocalVariableTableDemo {



    public static int staticTest(String param) {
        Object object = new Object();
        int num = 120;
        return num;
    }

    public double noStaticTest() {
        Object object = new Object();
        double num = 12.12;
        return num;
    }
}
