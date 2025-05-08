package com.guorong.stack.factory.enums;

import com.guorong.stack.ArrayStack;
import com.guorong.stack.IStack;
import com.guorong.stack.LinkedStack;
import com.guorong.stack.factory.IStackProvider;

import java.util.function.IntFunction;

/**
 * 栈类型枚举
 */
public enum StackType implements IStackProvider {
    ARRAY(ArrayStack::new, ArrayStack.class),
    LINKED(capacity -> new LinkedStack<>(), LinkedStack.class);
    private final IntFunction<? extends IStack> stackSupplier;

    private final Class<? extends IStack> clazz;

    StackType(IntFunction<? extends IStack> stackSupplier, Class<? extends IStack> clazz) {
        this.stackSupplier = stackSupplier;
        this.clazz = clazz;
    }

    @Override
    public <T> IStack<T> createStack(int capacity) {
        return (IStack<T>) stackSupplier.apply(capacity);
    }

    @Override
    public String getType() {
        return clazz.getName();
    }
}
