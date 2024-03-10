package com.guorong.inner_class.demo_3;

import org.junit.jupiter.api.Test;

/**
 * 测试 内部类 默认通过 DoThis.this 获取外部类的引用
 * @author guorong
 * @date 2021-08-10
 */
public class DoThisTest {

    @Test
    public void test() {
        DoThis doThis = new DoThis();
        // 获取内部类
        DoThis.Inner inner = doThis.getInner();
        // 通过内部类获取外部类的引用
        DoThis targetDoThis = inner.getDoThis();
        targetDoThis.print();

    }
}
