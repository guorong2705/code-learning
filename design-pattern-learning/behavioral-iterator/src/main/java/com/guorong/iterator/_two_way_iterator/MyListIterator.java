package com.guorong.iterator._two_way_iterator;

/**
 * 自定义双向迭代器接口
 */
interface MyListIterator<E> {

    /**
     * 是否有下一个元素
     * @return
     */
    boolean hasPrevious();

    /**
     * 获取上一个元素
     * @return
     */
    E previous();

    /**
     * 是否有下一个元素
     * @return
     */
    boolean hasNext();

    /**
     * 获取下一个元素
     * @return
     */
    E next();


}
