package com.guorong.list;

/**
 * 线性表的抽象实现类：定义了子类共享的方法和属性
 *
 * @param <T> 元素类型
 */
abstract class AbstractILinearList<T> implements ILinearList<T> {

    protected AbstractILinearList() {
    }

    /**
     * 当前元素数量
     */
    protected int size;

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 检查索引有效范围
     *
     * @param index 要检查的索引，范围为 0 <= index < size
     */
    protected void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds. Valid range is [0, " + (size - 1) + "], but found: " + index);
        }
    }

    /**
     * 为添加元素，检查索引范围
     *
     * @param index 要检查的索引，范围为 0 < index <= size
     */
    protected void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds. Valid range is [0, " + size + "], but found: " + index);
        }
    }
}
