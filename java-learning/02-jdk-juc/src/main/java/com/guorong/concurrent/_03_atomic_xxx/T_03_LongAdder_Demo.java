package com.guorong.concurrent._03_atomic_xxx;

import java.util.concurrent.atomic.LongAdder;

class T_03_LongAdder_Demo {

    private static LongAdder longAdder = new LongAdder();


    public static void main(String[] args) {

        longAdder.increment();

        System.out.println(longAdder.longValue());



    }


}
