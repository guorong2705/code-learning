package com.guorong.class_07;

import org.junit.jupiter.api.Test;

/**
 * 二叉树遍历使用递归（先序、中序、后续）
 */
class BinaryTreeTraversalRecursion {
    private static class Node {
        private int value;
        private Node left;
        private Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    // ================================================
    // 先序遍历
    void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    @Test
    void testPreTraversal() {
        Node root = new Node(6);
        Node l1 = new Node(4);
        Node r1 = new Node(7);
        root.left = l1;
        root.right = r1;
        preOrder(root);
    }


    // ================================================
    // 2.中序遍历
    void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }

    @Test
    void testInOrder() {
        Node root = new Node(6);
        Node l1 = new Node(4);
        Node r1 = new Node(7);
        root.left = l1;
        root.right = r1;
        inOrder(root);
    }

    // ================================================
    // 3. 后续遍历
    void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + " ");
    }
    @Test
    void testPostOrder() {
        Node root = new Node(6);
        Node l1 = new Node(4);
        Node r1 = new Node(7);
        root.left = l1;
        root.right = r1;
        postOrder(root);
    }

}
