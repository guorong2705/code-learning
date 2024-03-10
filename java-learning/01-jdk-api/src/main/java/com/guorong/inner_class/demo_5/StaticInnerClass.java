package com.guorong.inner_class.demo_5;

public class StaticInnerClass {

    public void print() {
        System.out.println("print()");
    }

    public static class Demo {

        public static void main(String[] args) {
            StaticInnerClass staticInnerClass = new StaticInnerClass();
            staticInnerClass.print();
        }
    }


}
