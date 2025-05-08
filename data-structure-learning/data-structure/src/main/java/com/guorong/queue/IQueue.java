package com.guorong.queue;

/**
 * 队列接口
 */
interface IQueue<T> {

    /**
     * 入队
     * @param element 入队元素
     * @throws IllegalStateException 如果队列已满（对于固定容量的队列）
     */
    void enqueue(T element);

    /**
     * 出队
     * @return 取出元素
     * @throws java.util.NoSuchElementException 如果队列为空
     */
    T dequeue();

    /**
     * 查看队首元素，但不移除
     * @return 队首元素
     * @throws java.util.NoSuchElementException 如果队列为空
     */
    T peek();

    /**
     * 判断队列是否为空
     * @return 队列为空，返回true，否则返回false
     */
    boolean isEmpty();

    /**
     * 判断队列是否满了
     * @return 队列满了返回true，否则返回false
     */
    boolean isFull();

    /**
     * 返回队列中元素个数
     * @return 元素个数
     */
    int size();

    /**
     * 清空队列中的所有元素
     */
    void clear();
}
