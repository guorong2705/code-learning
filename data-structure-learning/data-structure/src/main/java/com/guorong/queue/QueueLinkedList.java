package com.guorong.queue;

/**
 * 使用链表实现队列
 */
class QueueLinkedList<T> implements IQueue<T> {

    // 头指针
    private final Node head;
    // 尾指针
    private Node tail;
    // 元素个数
    private int size;

    public QueueLinkedList() {
        this.head = new Node(null);
        this.tail = head;
        this.size = 0;
    }

    @Override
    public void enqueue(T element) {
        Node node = new Node(element);
        tail.next = node;
        tail = node;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("cannot dequeue: queue is empty");
        }
        Node node = head.next;
        head.next = node.next;
        if (head.next == null) {
            this.tail = head;
        }
        size--;
        return node.value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("cannot peek: queue is empty");
        }
        return head.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return false; // 链表实实现队列，无容量限制
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head.next = null;
        this.tail = null;
    }

    // 节点定义
    private class Node {
        private T value;
        private Node next;

        public Node(T value) {
            this.value = value;
        }
    }
}
