package com.guorong.tree;

/**
 * 红黑树接口，仅存储值，不允许重复元素
 * 红黑树（Red-Black Tree）是一种自平衡的二叉搜索树
 * @param <T> 值的类型，必须实现Comparable接口以支持比较
 */
public interface IRedBlackTree<T extends Comparable<T>> {

    /**
     * 向红黑树中插入一个值
     * 如果值已存在，则忽略插入
     * @param value 值
     */
    boolean insert(T value);

    /**
     * 删除指定值对应的节点
     * @param value 值
     * @return 如果节点存在并被删除，返回true；否则返回false
     */
    boolean remove(T value);

    /**
     * 检查红黑树是否包含指定值
     * @param value 值
     * @return 如果值存在，返回true；否则返回false
     */
    boolean contains(T value);

    /**
     * 检查红黑树是否为空
     * @return 如果树为空，返回true；否则返回false
     */
    boolean isEmpty();

    /**
     * 获取红黑树的大小（节点数）
     * @return 树的节点数
     */
    int size();

    /**
     * 获取红黑树的高度
     * @return 树的高度
     */
    int height();

    /**
     * 验证当前树是否满足红黑树的性质
     * @return 如果满足红黑树性质，返回true；否则返回false
     */
    boolean isValidRedBlackTree();
}