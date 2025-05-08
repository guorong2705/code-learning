package com.guorong.tree;

import java.util.Comparator;
import java.util.List;

/**
 * AVL树接口，定义了自平衡二叉搜索树的标准操作。
 * 该树通过保持平衡保证所有操作的时间复杂度为 O(logN)。
 *
 * @param <T> 树中存储的元素类型，必须实现 Comparable 接口
 */
public interface IAVLTree<T extends Comparable<T>> {

    /**
     * 向AVL树中插入一个新元素。
     * 如果元素已存在，树保持不变。
     *
     * @param element 要插入的元素
     * @return 如果元素成功插入返回 true，如果元素已存在返回 false
     */
    boolean insert(T element);

    /**
     * 从AVL树中删除一个元素。
     * 如果元素不存在，树保持不变。
     *
     * @param element 要删除的元素
     * @return 如果元素成功删除返回 true，如果元素不存在返回 false
     */
    boolean remove(T element);

    /**
     * 在AVL树中搜索一个元素。
     *
     * @param element 要搜索的元素
     * @return 如果元素存在于树中返回 true，否则返回 false
     */
    boolean contains(T element);

    /**
     * 返回AVL树的高度。
     * 空树的高度为 -1，单节点树的高度为 0。
     *
     * @return 树的高度
     */
    int getHeight();

    /**
     * 返回AVL树中元素的数量。
     *
     * @return 树的元素数量
     */
    int getSize();

    /**
     * 检查AVL树是否为空。
     *
     * @return 如果树为空返回 true，否则返回 false
     */
    boolean isEmpty();

    /**
     * 清空AVL树中的所有元素，重置为空状态。
     */
    void clear();

    /**
     * 验证 AVL 树的平衡性和高度正确性
     * @return 如果树是合法的 AVL 树，返回true；否则返回false
     */
    boolean isValidAVLTree();

    /**
     * 前序遍历
     * @return 包含前序遍历元素的列表
     */
    List<T> preOrderTraversal();

    /**
     * 返回AVL树的中序遍历结果列表。
     * 元素按升序排列返回。
     *
     * @return 包含中序遍历元素的列表
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


}