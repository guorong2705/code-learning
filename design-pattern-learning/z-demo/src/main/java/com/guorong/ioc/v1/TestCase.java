package com.guorong.ioc.v1;

abstract class TestCase {

    public void run() {
        if (doTest()) {
            System.out.println("do test success --->>>");
        } else {
            System.out.println("do test fail --->>>");
        }
    }

    public abstract boolean doTest();
}
