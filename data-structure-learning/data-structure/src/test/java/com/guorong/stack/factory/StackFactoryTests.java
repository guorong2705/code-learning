package com.guorong.stack.factory;

import com.guorong.stack.ArrayStack;
import com.guorong.stack.IStack;
import com.guorong.stack.factory.enums.EnumStackProviderStrategy;

public class StackFactoryTests {
    public static void main(String[] args) {
        StackFactory stackFactory = new StackFactory(new EnumStackProviderStrategy());
        IStack<Integer> stack = stackFactory.createStack(ArrayStack.class, 20);
        stack.push(11);
    }
}
