package com.guorong.stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;

/**
 * 使用数组实现栈功能，并且支持动态数组扩容
 *
 * @param <T> 元素类型
 */
public class ArrayStack<T> extends AbstractStack<T>
        implements IStack<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 10000 * 10;
    private static final int DEFAULT_TOP = -1;
    // 数据数组
    private Object[] elements;
    // 栈容量
    private int capacity;

    // 指向栈顶元素位置
    private int top;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int initCapacity) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initCapacity);
        }
        if (initCapacity < DEFAULT_CAPACITY) {
            initCapacity = DEFAULT_CAPACITY;
        }
        if (initCapacity > MAX_CAPACITY) {
            initCapacity = MAX_CAPACITY;
        }
        this.elements = new Object[initCapacity];
        this.capacity = initCapacity;
        this.top = DEFAULT_TOP;
    }

    @Override
    public void push(T element) {
        Objects.requireNonNull(element, "element is not null");
        // 确保容量够用
        ensureCapacity(size + 1);
        elements[++top] = element;
        size++;
    }

    /**
     * 确保容量够用
     *
     * @param minCapacity 最小需要容量
     */
    private void ensureCapacity(int minCapacity) {
        if (capacity - minCapacity < 0) {
            grow(minCapacity);
        }
    }

    /**
     * 扩容
     *
     * @param minCapacity 需要最小容量
     */
    private void grow(int minCapacity) {
        int newCapacity = capacity + (capacity >> 1);
        // 获取容量最大值
        newCapacity = Math.max(newCapacity, minCapacity);

        if (newCapacity - MAX_CAPACITY > 0) {
            throw new OutOfMemoryError(
                    String.format("Stack capacity too large, current=%d, requested=%d, max=%d",
                            capacity, newCapacity, MAX_CAPACITY));
        }
        // 复制原数组内容到新数组
        this.elements = Arrays.copyOf(elements, newCapacity);
        this.capacity = newCapacity;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T element = topElement();
        // 清理引用，防止内存泄露
        elements[top] = null;
        top--;
        size--;
        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return topElement();
    }

    /**
     * 获取栈顶元素
     *
     * @return 返回栈顶元素
     */
    @SuppressWarnings("unchecked")
    private T topElement() {
        return (T) elements[top];
    }

    @Override
    public void clear() {
        // 清空数据元素引用
        Arrays.fill(elements, 0, size, null); // 仅清空有效元素
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        this.top = DEFAULT_TOP;
    }
}
