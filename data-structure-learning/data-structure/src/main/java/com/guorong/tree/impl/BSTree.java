package com.guorong.tree.impl;

import com.guorong.ArrayUtils;
import com.guorong.tree.IBSTree;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

import java.util.*;

/**
 * 二叉搜索树实现
 *
 * @param <T>
 */
public class BSTree<T extends Comparable<T>> implements IBSTree<T> {

    // 元素个数
    private int size;
    // 根节点
    private BSTNode root;

    private final Comparator<T> comparator;

    public BSTree() {
        this(null);
    }

    public BSTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.size = 0;
    }

    public boolean insert(T value) {
        int oldSize = size;
        int random = ArrayUtils.generateRandInt(1);
        if (random == 1) {
            root = insertRec(root, value);
        } else {
            insertIter(value);
        }
        return size == oldSize + 1;
    }

    /**
     * 递归插入
     *
     * @param node  插入树的头节点
     * @param value 插入值
     * @return 返回根节点
     */
    private BSTNode insertRec(BSTNode node, T value) {
        if (value == null) {
            throw new IllegalArgumentException("cannot insert null value");
        }
        if (node == null) { // 找到末尾创建节点返回
            size++;
            return new BSTNode(value);
        }
        // 递归查找到value插入的父节点
        int compare = compare(value, node.value);
        if (compare < 0) {
            node.left = insertRec(node.left, value);
        } else if (compare > 0) {
            node.right = insertRec(node.right, value);
        }
        return node;
    }

    /**
     * 迭代插入值
     *
     * @param value 要插入的值，value不能为null
     */
    private void insertIter(T value) {
        if (value == null) {
            throw new IllegalArgumentException("cannot insert null value");
        }
        BSTNode newNode = new BSTNode(value);
        if (isEmpty()) {
            root = newNode;
            size++;
            return;
        }
        // 查找要插入值得父亲节
        BSTNode parent = null;
        BSTNode current = root;
        while (current != null) {
            parent = current;
            if (compare(value, current.value) < 0) {
                current = current.left;
            } else if (compare(value, current.value) > 0) {
                current = current.right;
            } else { // 重复不插入
                return;
            }
        }
        // 插入value节点
        if (compare(value, parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
    }


    @Override
    public boolean remove(T value) {
        // 空值或空树，直接返回
        if (root == null || value == null) {
            return false;
        }
        // 步骤1：找到目标节点和它的父节点
        BSTNode parent = null;
        BSTNode current = root;
        while (current != null) {
            int compare = compare(value, current.value);
            // 找到节点
            if (compare == 0) {
                break;
            }
            // 继续往下查找
            parent = current;
            current = compare < 0 ? current.left : current.right;
        }

        // 没找到目标节点
        if (current == null) {
            return false;
        }

        // 步骤2：删除节点
        // 情况1：有两个子节点
        if (current.left != null && current.right != null) {
            // 找到右子树的最小值
            BSTNode minBSTNodeParent = current;
            BSTNode minBSTNode = current.right;
            while (minBSTNode.left != null) {
                minBSTNodeParent = minBSTNode;
                minBSTNode = minBSTNode.left;
            }
            // 用最小节点的值替换当前节点
            current.value = minBSTNode.value;
            // 删除最小节点
            if (minBSTNode == minBSTNodeParent.left) { // 节点(current.right)存在左节点
                minBSTNodeParent.left = minBSTNode.right;
            } else { // 节点(current.right)不存在
                minBSTNodeParent.right = minBSTNode.right;
            }
        }
        // 情况1：没有叶子节点或只有一个子节点
        else {
            BSTNode child = current.left != null ? current.left : current.right;
            if (current == root) {
                this.root = child; // 替换根
            } else if (current == parent.left) {
                parent.left = child; // 父节点的左指针指向子节点
            } else {
                parent.right = child; // 父节点的右指针指向子节点
            }
        }
        size--;
        return true;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    private final Random random = new Random();

    @Override
    public int height() {
        int randomNum = random.nextInt(2);
        int height = 0;
        switch (randomNum) {
            case 0:
                height = heightRecursion(root);
                break;
            case 1:
                height = heightQueue();
                break;
            default:
                break;
        }
        return height;
    }

    // 求树高度的递归方法
    private int heightRecursion(BSTNode BSTNode) {
        if (BSTNode == null) {
            return 0;
        }
        int leftHeight = heightRecursion(BSTNode.left);
        int rightHeight = heightRecursion(BSTNode.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 使用队列实现
    private int heightQueue() {
        int height = 0;
        if (root == null) {
            return height;
        }
        Queue<BSTNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // 当前层的节点数
            height++;
            // 处理当前层的所有节点
            for (int i = 0; i < levelSize; i++) {
                BSTNode current = queue.poll();
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        return height;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    // 前序遍历
    @Override
    public List<T> preOrderTraversal() {
        List<T> list = new ArrayList<>();
        preOrderTraversal(list, root);
        return list;
    }

    // 前序遍历递归函数
    private void preOrderTraversal(List<T> list, BSTNode BSTNode) {
        if (BSTNode == null) {
            return;
        }
        list.add(BSTNode.value);
        preOrderTraversal(list, BSTNode.left);
        preOrderTraversal(list, BSTNode.right);
    }

    @Override
    public List<T> inOrderTraversal() {
        List<T> list = new ArrayList<>();
        inOrderTraversal(list, root);
        return list;
    }

    // 中序遍历递归函数
    private void inOrderTraversal(List<T> list, BSTNode bstNode) {
        if (bstNode == null) {
            return;
        }
        inOrderTraversal(list, bstNode.left);
        list.add(bstNode.value);
        inOrderTraversal(list, bstNode.right);
    }

    @Override
    public List<T> postOrderTraversal() {
        ArrayList<T> list = new ArrayList<>();
        postOrderTraversal(list, root);
        return list;
    }

    // 后续遍历递归函数
    private void postOrderTraversal(List<T> list, BSTNode BSTNode) {
        if (BSTNode == null) {
            return;
        }
        preOrderTraversal(list, BSTNode.left);
        preOrderTraversal(list, BSTNode.right);
        list.add(BSTNode.value);
    }

    @Override
    public List<T> levelOrderTraversal() {
        ArrayList<T> list = new ArrayList<>();
        if (isEmpty()) {
            return list;
        }
        Queue<BSTNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BSTNode current = queue.poll();
            list.add(current.value);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return list;
    }


    @Override
    public Comparator<? super T> getComparator() {
        return comparator;
    }

    @Override
    public boolean isValidBST() {
        return validBSTRec(root);
    }

    private boolean validBSTRec(BSTNode node) {
        if (node == null) {
            return true;
        }
        // 判断当前节点是否符合规则
        BSTNode leftNode = node.left;
        BSTNode rightNode = node.right;
        if (leftNode != null && compare(leftNode.value, node.value) >= 0) {
            return false;
        }
        if (rightNode != null && compare(rightNode.value, node.value) <= 0) {
            return false;
        }
        // 递归验证左右节点
        return validBSTRec(leftNode) && validBSTRec(rightNode);
    }


    @Override
    public boolean contains(T value) {
        return contains(root, value);
    }

    // 包含递归方法
    private boolean contains(BSTNode root, T value) {
        if (root == null || value == null) {
            return false;
        }
        int compare = compare(value, root.value);
        if (compare < 0) {
            return contains(root.left, value);
        } else if (compare > 0) {
            return contains(root.right, value);
        } else {
            return true;
        }
    }

    // 使用迭代
    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        T min = null;
        // 最左边左节点就是最小值
        BSTNode current = root;
        while (current != null) {
            min = current.value;
            current = current.left;
        }
        return min;
    }

    // 使用递归
    @Override
    public T getMax() {
        return getMax(root);
    }

    // 递归方法
    private T getMax(BSTNode BSTNode) {
        if (BSTNode == null) {
            return null;
        }
        if (BSTNode.right == null) {
            return BSTNode.value;
        }
        return getMax(BSTNode.right);
    }


    /**
     * 比较两个对象
     *
     * @param a 第一个元素
     * @param b 第二个元素
     * @return t1小于t2 返回负数, t1大于t2返回正数，t1等于t2返回0
     */
    private int compare(T a, T b) {
        if (this.comparator != null) {
            return Objects.compare(a, b, comparator);
        }
        return a.compareTo(b);
    }


    // 二叉搜索树节点定义
    private class BSTNode {
        T value;
        BSTNode left;
        BSTNode right;

        public BSTNode(T value) {
            this.value = value;
        }
    }

}
