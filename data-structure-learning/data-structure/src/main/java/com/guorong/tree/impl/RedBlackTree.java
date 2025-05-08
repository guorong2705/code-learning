package com.guorong.tree.impl;

import com.guorong.tree.IRedBlackTree;

import java.util.Comparator;

public class RedBlackTree<T extends Comparable<T>> implements IRedBlackTree<T> {

    // 表示空节点
    private final Node NIL = new Node(null, Color.BLACK);

    private Node root;

    private int size;

    private Comparator<? super T> comparator;

    public RedBlackTree() {
        this(null);
    }

    public RedBlackTree(Comparator<? super T> comparator) {
        this.root = NIL;
        this.comparator = comparator;
        this.size = 0;
    }

    @Override
    public boolean insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value is not null");
        }
        // 判断是否为空树
        if (root == NIL) {
            root = new Node(value, Color.BLACK);
            root.left = NIL;
            root.right = NIL;
            size++;
            return true;
        }
        return insertRec(root, value);
    }

    // 递归插入节点
    private boolean insertRec(Node node, T value) {
        int compare = compare(value, node.value);
        if (compare == 0) { // 重复
            return false;
        }
        // 节点插入左侧
        if (compare < 0) {

        }
        // 节点插入右侧
        return true; // todo

    }

    @Override
    public boolean remove(T value) {
        return false;
    }

    @Override
    public boolean contains(T value) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public boolean isValidRedBlackTree() {
        return false;
    }

    /**
     * 比较a和b的大小
     *
     * @param a
     * @param b
     * @return a大于b返回1，a小于b返回-1，a等于b返回0
     */
    private int compare(T a, T b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("a or b not null");
        }
        return comparator != null ? comparator.compare(a, b) : a.compareTo(b);
    }

    /**
     * 更新节点高度
     *
     * @param node 要更新的节点高度
     */
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }


    /**
     * 获取节点高度
     *
     * @param node 红黑树节点
     * @return 节点高度
     */
    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private enum Color {
        RED, BLACK;
    }

    // 红黑树节点
    private class Node {
        T value;
        int height;
        Color color;
        Node left;
        Node right;
        Node parent;

        public Node(T value) {
            this(value, Color.RED);
        }

        public Node(T value, Color color) {
            this.value = value;
            this.height = 0;
            this.color = color;
        }
    }

}
