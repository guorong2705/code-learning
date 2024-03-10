package com.guorong._collection;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class GArrayList<E> implements Serializable, Iterable<E> {

    // 存储元素数组
    private Object[] elementData;

    // 空数组
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    // 默认容量空数组
    private static final Object[] DEFAULT_CAPACITY_ELEMENT_DATA = {};

    // 默认数组容量
    private static final int DEFAULT_CAPACITY = 10;

    // 元素个数
    private int size;

    // 集合修改次数
    private int modCount;

    public GArrayList() {
        elementData = DEFAULT_CAPACITY_ELEMENT_DATA;
    }

    public GArrayList(int initCapacity) {
        if (initCapacity > 0) {
            elementData = new Object[initCapacity];
        } else if (initCapacity == 0) {
            elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException(String.format("illegal argument initCapacity: %s", initCapacity));
        }
    }

    // 添加元素: 从集合尾部追加元素
    public boolean add(E element) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = element;
        modCount++;
        return true;
    }

    // 在指定位置添加元素：位置范围[0-size]
    public void add(int index, E element) {
        rangeCheckForAdd(index); // 检查索引范围
        ensureCapacityInternal(size + 1); // 确认内部容量是否够用
        int numMoved = size - index; // 要往后移动的数量
        if (numMoved > 0) { // 不是最后一个需要移动元素
            System.arraycopy(elementData, index, elementData, index + 1, numMoved);
        }
        elementData[index] = element;
        size++;
        modCount++;
    }

    // 追加集合元素到集合中
    public void addAll(Collection<? extends E> collection) {
        Object[] array = collection.toArray(); // 转换为数组
        int numNew = array.length; // 要添加元素的数量
        ensureCapacityInternal(size + numNew); // 确认容量
        // 复制数据到数组中
        System.arraycopy(array, 0, elementData, size, numNew);
        size += numNew;
        modCount++;
    }

    // 在指定位置插入集合
    public void addAll(int index, Collection<? extends E> collection) {
        rangeCheckForAdd(index); // 检查索引范围
        Object[] array = collection.toArray(); // 转换为数组
        int numNew = array.length; // 添加元素数量
        ensureCapacityInternal(size + numNew);// 确认集合容量
        if (size - index > 0) {
            // 中间插入需要往后面移动元素
            System.arraycopy(elementData, index, elementData, index + numNew, numNew);
        }
        // 复制数据到数组中
        System.arraycopy(array, 0, elementData, index, numNew);
        size += numNew;
        modCount++;
    }

    // 删除指定索引处的元素
    public Object remove(int index) {
        rangeCheck(index); // 检查索引范围
        modCount++;
        Object oldValue = elementData(index); // 数组中的旧值
        int numMoved = size - index - 1; // 要移动元素的数量
        if (numMoved > 0) { // 如果不是最后一个数据, index 后面的数据往前移动一位
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[size--] = null; // 设置为null, GC回收垃圾
        return oldValue;
    }

    // 删除第一个匹配的元素
    public boolean remove(E element) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(element, elementData(index))) {
                remove(index);// 删除指定索引元素
                return true;
            }
        }
        return false;
    }

    // 删除集合中的元素
    public void removeAll(Collection<? extends E> collection) {
        Objects.requireNonNull(collection, "collection not is null");
        Object[] array = collection.toArray();
        for (int i = 0; i < array.length; i++) {
            remove((E) array[i]);
        }
    }

    // 获取元素的索引
    public int indexOf(E element) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(element, elementData(index))) {
                return index;
            }
        }
        return -1;
    }

    // 获取最后一次出现元素的索引
    public int lastIndexOf(E element) {
        for (int index = size - 1; index >= 0; index--) {
            if (Objects.equals(element, elementData(index))) {
                return index;
            }
        }
        return -1;
    }

    // 获取集合元素个数
    public int size() {
        return size;
    }

    // 判断集合是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 检查索引范围
    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(String.format("index out of bound, size: %s  index: %s", size, index));
        }
    }

    // 添加元素时候检查范围
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("index out of bound, size: %s  index: %s", size, index));
        }
    }

    // 确认内部容量
    private void ensureCapacityInternal(int minCapacity) {
        // 判断是否为默认容量空数组
        if (elementData == DEFAULT_CAPACITY_ELEMENT_DATA) {
            minCapacity = Math.max(minCapacity, DEFAULT_CAPACITY); // 获取默认容量和最小容量的最大值
        }
        // 判断是否需要扩容数组容量
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    // 扩容数组
    private void grow(int minCapacity) {
        modCount++;
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); // 扩容为原来容量的1.5倍
        if (minCapacity - newCapacity > 0) { // 判断扩容后的容量是否够用
            newCapacity = minCapacity;
        }
        elementData = Arrays.copyOf(elementData, newCapacity); // 复制数组
    }

    // 获取元素
    private E elementData(int index) {
        return (E) elementData[index];
    }

    // 获取迭代器
    @Override
    public Iterator<E> iterator() {
        return new GArrayListIterator();
    }

    private class GArrayListIterator implements Iterator<E> {

        // 光标
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor > size;
        }

        @Override
        public E next() {
            return elementData(cursor++);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                sb.append(elementData[i]);
            } else {
                sb.append(elementData[i]).append(",");
            }
        }
        return "[" + sb + "]";
    }


    public static void main(String[] args) {
        GArrayList<String> arrayList = new GArrayList();
        arrayList.add("001");
        arrayList.add("002");
        arrayList.add("003");
        arrayList.add("004");
        arrayList.add("005");
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }


}
