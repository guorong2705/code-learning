package com.guorong.class_08;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 判断一个二叉树是否是平衡树
 */
class _01_BinaryTreeIsBalance {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    private static class NodeInfo {
        boolean isBalance;
        int height;

        public NodeInfo(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    boolean isBalanceTree(Node root) {
        NodeInfo nodeInfo = process(root);
        return nodeInfo.isBalance;
    }

    private NodeInfo process(Node node) {
        if (node == null) {
            return new NodeInfo(true, 0);
        }
        // 左子树的信息
        NodeInfo leftInfo = process(node.left);
        // 右子树的信息
        NodeInfo rightInfo = process(node.right);

        boolean isBalance = leftInfo.isBalance && rightInfo.isBalance && Math.abs(leftInfo.height - rightInfo.height) <= 1;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new NodeInfo(isBalance, height);
    }

    @Test
    void testIsBalanceTree() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.right = new Node(4);
        Assertions.assertTrue(isBalanceTree(root));
    }

}
