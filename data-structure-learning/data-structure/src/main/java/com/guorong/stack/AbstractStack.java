package com.guorong.stack;

/**
 * 定义栈抽象类，提供子类共享的方法和字段
 *
 * @param <T> 元素类型
 */
public abstract class AbstractStack<T> implements IStack<T> {

    protected AbstractStack() {
    }

    /**
     * 元素数量
     */
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 提供默认实现方法，如果需要限制栈容量，请重新该方法
     * @return 如果栈满了返回true，否则返回false
     */
    @Override
    public boolean isFull() {
        return false;
    }
}
