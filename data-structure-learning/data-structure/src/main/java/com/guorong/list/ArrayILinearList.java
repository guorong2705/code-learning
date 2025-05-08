package com.guorong.list;

import java.util.Arrays;
import java.util.Objects;

/**
 * 基于数组实现的顺序表
 *
 * @param <T> 元素类型
 */
class ArrayILinearList<T> extends AbstractILinearList<T>
        implements ILinearList<T> {

    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 10;

    // 最大数组容量
    private static final int MAX_ARRAY_CAPACITY = Integer.MAX_VALUE - 8;

    // 共享空数组实例
    private static final Object[] EMPTY_ELEMENTS = {};

    // 存储元素数组
    private Object[] elements;


    /**
     * 构造一个默认容量的空列表。
     */
    public ArrayILinearList() {
        // 使用默认容量
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * 构造一个指定初始容量的空列表。
     *
     * @param initialCapacity 初始容量
     * @throws IllegalArgumentException 如果初始容量为负数
     */
    public ArrayILinearList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elements = EMPTY_ELEMENTS;
        } else {
            throw new IllegalArgumentException("illegal capacity: " + initialCapacity);
        }
    }

    /**
     * 添加元素
     * @param element 要添加的元素，element 支持 null
     */
    @Override
    public void add(T element) {
        // 确保内部容量
        ensureCapacity(size + 1);
        // 添加元素
        elements[size++] = element;
    }

    /**
     * 在指定索引处，添加元素
     * @param index   插入位置的索引，范围为 0 <= index <= size()
     * @param element 要插入的元素，element 支持为 null
     */
    @Override
    public void add(int index, T element) {
        // 索引范围检查
        checkIndexForAdd(index);
        // 确保内部容量
        ensureCapacity(size + 1);
        // 将index处以后的元素后移一位
        int moveLen = size - index;
        System.arraycopy(elements, index, elements, index + 1, moveLen);
        // 设置index索引处元素
        elements[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        // 检查索引范围
        checkIndexRange(index);
        T removeElem = getElement(index);
        // index后面的元素，往前移动一位
        int moveLen = size - index - 1;
        if (moveLen > 0) {
            System.arraycopy(elements, index + 1, elements, index, moveLen);
        }
        // 清空删除元素指针，防止内存溢出
        elements[--size] = null;
        return removeElem;
    }

    @Override
    public boolean remove(T element) {
        // 查找第一次出现索引
        int removeIdx = indexOf(element);
        if (removeIdx >= 0) {
            remove(removeIdx);
            return true;
        }
        return false;
    }

    @Override
    public T get(int index) {
        checkIndexRange(index); // 检查索引范围
        return getElement(index);
    }

    @Override
    public T set(int index, T element) {
        // 检查索引范围
        checkIndexRange(index);
        T oldElem = getElement(index);
        elements[index] = element;
        return oldElem;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        // 从后面往前找
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);
        this.size = 0;
    }

    /**
     * 手动缩容操作，将容量缩小到元素个数
     */
    public void trimToSize() {
        if (size < elements.length && elements != EMPTY_ELEMENTS) {
            elements = (size == 0) ? EMPTY_ELEMENTS : Arrays.copyOf(elements, size);
        }
    }

    /**
     * 获取指定索引处的元素
     *
     * @param index 索引
     * @return 索引处的值
     */
    @SuppressWarnings("unchecked")
    private T getElement(int index) {
        return (T) elements[index];
    }


    /**
     * 确保数组满足最小容量
     *
     * @param minCapacity 最小容量
     */
    private void ensureCapacity(int minCapacity) {
        // 大于当前数组容量进行扩容操作
        if (minCapacity - elements.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * 对数组进行扩容，如果需要扩容，使用1.5倍增长因子进行扩容来满足最小容量需要
     *
     * @param minCapacity 最小容量
     */
    private void grow(int minCapacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); // 1.5 倍扩容

        // 获取容量中较大的
        newCapacity = Math.max(newCapacity, minCapacity);

        // 超过最大容量
        if (newCapacity - MAX_ARRAY_CAPACITY > 0) {
            throw new OutOfMemoryError("required array size too large");
        }
        // 复制数据
        elements = Arrays.copyOf(elements, newCapacity);
    }
}
