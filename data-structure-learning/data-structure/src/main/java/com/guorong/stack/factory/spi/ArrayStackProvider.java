package com.guorong.stack.factory.spi;

import com.guorong.stack.ArrayStack;
import com.guorong.stack.IStack;
import com.guorong.stack.factory.IStackProvider;

/**
 * ArrayStack 实例提供者
 */
public class ArrayStackProvider implements IStackProvider {

    @Override
    public <T> IStack<T> createStack(int capacity) {
        return new ArrayStack<>(capacity);
    }

    @Override
    public String getType() {
        return ArrayStack.class.getName();
    }
}
