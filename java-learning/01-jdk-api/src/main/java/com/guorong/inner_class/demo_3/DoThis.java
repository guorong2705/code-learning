package com.guorong.inner_class.demo_3;

/**
 * @author guorong
 * @date 2021-08-10
 */
public class DoThis {

    public void print() {
        System.out.println("DoThis.....print");
    }

    public class Inner {
        public DoThis getDoThis() {
            return DoThis.this;
        }
    }

    public Inner getInner() {
        return new Inner();
    }
}
