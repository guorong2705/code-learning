package com.guorong.stack;

import java.util.EmptyStackException;
import java.util.Objects;

/**
 * 使用链表实现栈，采用哨兵节点简化插入和删除操作。
 * 哨兵节点不存储数据，仅作为链表头部的占位符。
 *
 * @param <T> 元素类型
 */
public class LinkedStack<T> extends AbstractStack<T>
        implements IStack<T> {

    /**
     * 头节点：使用哨兵节点
     */
    private final Node<T> head;

    public LinkedStack() {
        this.head = new Node<>(null);
    }

    @Override
    public void push(T element) {
        Objects.requireNonNull(element, "element cannot be null");
        Node<T> node = new Node<>(element);
        node.next = head.next;
        head.next = node;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T element = head.next.value;
        head.next = head.next.next;
        size--;
        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Node<T> node = head.next;
        return node.value;
    }

    @Override
    public void clear() {
        this.head.next = null;
        this.size = 0;
    }

    // 元素节点
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T data) {
            this.value = data;
        }
    }
}
