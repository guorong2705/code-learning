package com.guorong.tree;

import java.util.Comparator;
import java.util.List;

/**
 * 二叉搜索树完整接口，定义所有标准BST操作
 *
 * @param <T> 存储的元素类型，必须实现Comparable接口
 */
public interface IBSTree<T extends Comparable<T>> {

    /**
     * 插入新元素
     *
     * @param value 要插入的元素，不能为null
     * @throws IllegalArgumentException 如果value为null
     * @return 插入成功返回true，否则返回false
     */
    boolean insert(T value);

    /**
     * 删除指定元素
     *
     * @param value 要删除的元素
     * @return 如果成功删除返回true，否则返回false
     * @throws IllegalArgumentException 如果value为null
     */
    boolean remove(T value);

    /**
     * 检查是否包含指定元素
     *
     * @param value 要查找的元素
     * @return 如果树中包含该元素返回true，否则返回false
     */
    boolean contains(T value);

    /**
     * 获取树中最小元素
     *
     * @return 最小元素，如果树为空返回null
     */
    T getMin();

    /**
     * 获取树中最大元素
     *
     * @return 最大元素，如果树为空返回null
     */
    T getMax();

    /**
     * 检查树是否为空
     *
     * @return 如果树为空返回true
     */
    boolean isEmpty();

    /**
     * 获取树中节点数量
     *
     * @return 节点数量
     */
    int size();

    /**
     * 获取树的深度（高度）
     *
     * @return 树的深度，如果树为空返回0
     */
    int height();

    /**
     * 清空树
     */
    void clear();

    /**
     * 前序遍历
     *
     * @return 按前序遍历顺序返回的元素列表
     */
    List<T> preOrderTraversal();

    /**
     * 中序遍历
     *
     * @return 按中序遍历顺序返回的元素列表
     */
    List<T> inOrderTraversal();

    /**
     * 后序遍历
     *
     * @return 按后序遍历顺序返回的元素列表
     */
    List<T> postOrderTraversal();

    /**
     * 层序遍历
     *
     * @return 按层序遍历顺序返回的元素列表
     */
    List<T> levelOrderTraversal();

    /**
     * 获取比较器
     *
     * @return 当前使用的比较器，如果使用自然顺序则返回null
     */
    Comparator<? super T> getComparator();

    /**
     * 判断当前BST树是否有效
     * @return 满足二叉搜索树返回true，否则返回false
     */
    boolean isValidBST();
}