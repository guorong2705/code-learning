package com.guorong.stack.factory.spi;

import com.guorong.stack.IStack;
import com.guorong.stack.LinkedStack;
import com.guorong.stack.factory.IStackProvider;

/**
 * LinkedStack 实例提供者
 */
public class LinkedStackProvider implements IStackProvider {
    /**
     * 创建栈实例，因为使用链表实现栈，所以capacity创建实例时候将会被忽略
     *
     * @param capacity 栈容量，不支持
     * @return 栈实例
     */
    @Override
    public <T> IStack<T> createStack(int capacity) {
        return new LinkedStack<>();
    }

    @Override
    public String getType() {
        return LinkedStack.class.getName();
    }
}
