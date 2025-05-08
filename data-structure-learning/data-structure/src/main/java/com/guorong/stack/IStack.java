package com.guorong.stack;

/**
 * 定义栈操作接口。注意：实现类默认非线程安全，
 * 多线程使用需外部同步{@link StackUtils#synchronizedStack(IStack)}。
 * @param <T> 元素类型
 */
public interface IStack<T> {

    /**
     * 将元素压入栈顶
     * @param element 压入元素
     * @throws IllegalStateException 如果栈已经满了：isFull() 返回true
     */
    void push(T element);

    /**
     * 移除并返回栈顶元素
     * @return 栈顶元素
     * @throws java.util.EmptyStackException 如果栈是空的: isEmpty() 返回 true
     */
    T pop();

    /**
     * 查看栈顶元素
     * @return 栈顶元素
     * @throws java.util.EmptyStackException 如果栈是空的：isEmpty() 返回 true
     */
    T peek();

    /**
     * 检查栈是否为空
     * @return 栈空返回true，否则返回false
     */
    boolean isEmpty();

    /**
     * 检查栈是否已满
     * @return 栈满返回true，否则返回false
     */
    boolean isFull();

    /**
     * 返回当前元素个数
     * @return 当前元素个数
     */
    int size();

    /**
     * 清空栈
     */
    void clear();

}
