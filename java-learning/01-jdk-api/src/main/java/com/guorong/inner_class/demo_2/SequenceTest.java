package com.guorong.inner_class.demo_2;

import org.junit.jupiter.api.Test;

/**
 * 使用内部类实现选择器
 */
public class SequenceTest {

    @Test
    public void test01() {
        // 创建 Sequence 容器实例
        Sequence sequence = new Sequence(10);
        // 添加元素到容器中
        for (int i = 0; i < 10; i++) {
            sequence.add(Integer.valueOf(i));
        }

        // 获取选择器
        Selector selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            // 移动光标到下一个
            selector.next();
        }
    }

    @Test
    public void test02() {
        // 创建 Sequence 容器实例
        Sequence sequence = new Sequence(10);
        // 添加元素到容器中
        for (int i = 0; i < 10; i++) {
            sequence.add(Integer.valueOf(i));
        }
        Selector selector = sequence.reverseSelector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}
