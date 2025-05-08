package com.guorong.stack.factory;

import com.guorong.stack.IStack;

/**
 * IStack 实例提供者接口
 */
public interface IStackProvider {
    /**
     * 创建栈实例接口
     *
     * @param capacity 栈容量
     * @return 栈实现类实例
     */
    <T> IStack<T> createStack(int capacity);

    /**
     * 返回栈类型的唯一标识（通常为实现类的全限定名）。
     *
     * @return 类型标识
     */
    String getType();
}
