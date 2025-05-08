package com.guorong.list;

/**
 * 线性表操作接口，定义了线性表的基本和扩展操作。
 * 线性表是一个有序的元素序列，支持插入、删除、查找、遍历等操作。
 * 该接口适用于顺序表（如基于数组）和链表（如基于节点）等实现。
 *
 * @param <T> 线性表中元素的类型
 */
interface ILinearList<T> {

    /**
     * 在线性表末尾添加一个元素。
     *
     * @param element 要添加的元素
     * @throws IllegalStateException 如果线性表已满（适用于固定容量的实现）
     * @throws NullPointerException  如果 element 为 null 且实现不支持 null 元素
     */
    void add(T element);


    /**
     * 向线性表指定索引位置插入一个元素。
     *
     * @param index   插入位置的索引，范围为 0 <= index <= size()
     * @param element 要插入的元素
     * @throws IndexOutOfBoundsException 如果索引超出有效范围
     * @throws IllegalStateException     如果线性表已满（适用于固定容量的实现）
     * @throws NullPointerException      如果 element 为 null 且实现不支持 null 元素
     */
    void add(int index, T element);


    /**
     * 删除并返回指定索引位置的元素。
     *
     * @param index 要删除元素的索引，范围为 0 <= index < size()
     * @return 被删除的元素
     * @throws IndexOutOfBoundsException        如果索引超出有效范围
     * @throws java.util.NoSuchElementException 如果线性表为空
     */
    T remove(int index);

    /**
     * 删除首次出现的指定元素（如果存在）。
     *
     * @param element 要删除的元素
     * @return 如果元素被删除，返回 true；否则返回 false
     * @throws NullPointerException 如果 element 为 null 且实现不支持 null 元素
     */
    boolean remove(T element);

    /**
     * 获取指定索引位置的元素。
     *
     * @param index 要获取元素的索引，范围为 0 <= index < size()
     * @return 指定位置的元素
     * @throws IndexOutOfBoundsException        如果索引超出有效范围
     * @throws java.util.NoSuchElementException 如果线性表为空
     */
    T get(int index);

    /**
     * 修改指定索引位置的元素，并返回原元素。
     *
     * @param index   要修改元素的索引，范围为 0 <= index < size()
     * @param element 新元素值
     * @return 原位置的元素
     * @throws IndexOutOfBoundsException        如果索引超出有效范围
     * @throws java.util.NoSuchElementException 如果线性表为空
     * @throws NullPointerException             如果 element 为 null 且实现不支持 null 元素
     */
    T set(int index, T element);

    /**
     * 查找指定元素首次出现的索引。
     *
     * @param element 要查找的元素
     * @return 元素首次出现的索引，如果不存在返回 -1
     * @throws NullPointerException 如果 element 为 null 且实现不支持 null 元素
     */
    int indexOf(T element);

    /**
     * 查找指定元素最后出现的索引。
     *
     * @param element 要查找的元素
     * @return 元素最后出现的索引，如果不存在返回 -1
     * @throws NullPointerException 如果 element 为 null 且实现不支持 null 元素
     */
    int lastIndexOf(T element);

    /**
     * 检查线性表是否包含指定元素。
     *
     * @param element 要检查的元素
     * @return 如果包含指定元素返回 true，否则返回 false
     * @throws NullPointerException 如果 element 为 null 且实现不支持 null 元素
     */
    boolean contains(T element);

    /**
     * 返回线性表中的元素数量。
     *
     * @return 线性表的元素数量
     */
    int size();

    /**
     * 检查线性表是否为空。
     *
     * @return 如果线性表为空返回 true，否则返回 false
     */
    boolean isEmpty();

    /**
     * 清空线性表中的所有元素。
     */
    void clear();


}