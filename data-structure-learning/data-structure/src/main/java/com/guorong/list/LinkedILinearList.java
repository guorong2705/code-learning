package com.guorong.list;

import java.util.Objects;

/**
 * 使用双向链表实现的线性表，带头尾哨兵节点。
 * 注意：此实现非线程安全，多线程环境下需外部同步。
 *
 * @param <T> 元素类型
 */
class LinkedILinearList<T> extends AbstractILinearList<T>
        implements ILinearList<T> {

    /**
     * 头节点哨兵
     */
    private final Node<T> head = new Node<>(null);

    /**
     * 尾节点哨兵
     */
    private final Node<T> tail = new Node<>(null);

    public LinkedILinearList() {
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 添加元素
     * @param element 要添加的元素，支持 element支持 null
     */
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.prev = tail.prev;
        newNode.next = tail;
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
    }

    /**
     * 添加元素
     * @param index   插入位置的索引，范围为 0 <= index <= size()
     * @param element 要插入的元素， element 支持 null
     */
    @Override
    public void add(int index, T element) {
        // 检查范围
        checkIndexForAdd(index);
        Node<T> newNode = new Node<>(element);

        Node<T> prevNode = head;
        for (int i = 0; i < index; i++) {
            prevNode = prevNode.next;
        }
        // 新节点指针调整
        newNode.prev = prevNode;
        newNode.next = prevNode.next;

        prevNode.next.prev = newNode;
        prevNode.next = newNode;
        size++;
    }

    @Override
    public T remove(int index) {
        // 找到指定索引位置节点
        Node<T> removeNode = getNodeByIndex(index);
        // 调整关系
        removeNode.prev.next = removeNode.next;
        removeNode.next.prev = removeNode.prev;
        size--;
        return removeNode.element;
    }

    @Override
    public boolean remove(T element) {
        // 查找结点
        Node<T> currNode = head.next;
        while (currNode != tail && !Objects.equals(element, currNode.element)) {
            currNode = currNode.next;
        }

        if (currNode == tail) {
            return false; // 未找到元素
        }
        // 移除节点
        currNode.prev.next = currNode.next;
        currNode.next.prev = currNode.prev;
        size--;
        return true;
    }

    @Override
    public T get(int index) {
        return getNodeByIndex(index).element;
    }


    @Override
    public T set(int index, T element) {
        // 根据索引获取节点
        Node<T> node = getNodeByIndex(index);
        // 直接修改节点的值
        T oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    @Override
    public int indexOf(T element) {
        Node<T> currNode = head;
        for (int i = 0; i < size; i++) {
            currNode = currNode.next;
            if (Objects.equals(element, currNode.element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        Node<T> currNode = tail;
        for (int i = size - 1; i >= 0; i--) {
            currNode = currNode.prev;
            if (Objects.equals(currNode.element, element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        // 从此列表中删除所有元素。
        Node<T> currNode = head.next;
        while (currNode != tail) {
            Node<T> removeNode = currNode;
            currNode = currNode.next;
            removeNode.element = null;
            removeNode.prev = null;
            removeNode.next = null;
        }
        head.next = tail;
        tail.prev = head;
        size = 0;
    }


    /**
     * 获取指定索引处的节点
     *
     * @param index 查找索引
     * @return 找到的节点
     */
    private Node<T> getNodeByIndex(int index) {
        checkIndexRange(index);
        // 根据index位置选择遍历方式
        Node<T> currNode;
        if (index * 2 < size) {
            currNode = head;
            for (int i = 0; i <= index; i++) {
                currNode = currNode.next;
            }
        } else {
            currNode = tail;
            for (int i = size - 1; i >= index; i--) {
                currNode = currNode.prev;
            }
        }
        return currNode;
    }

    /**
     * 节点定义
     */
    private static class Node<T> {
        private T element;
        private Node<T> prev;
        private Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }
}
