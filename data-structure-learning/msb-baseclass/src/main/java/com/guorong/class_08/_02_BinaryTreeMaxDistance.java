package com.guorong.class_08;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给定一颗二叉树的头节点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离
 */
class _02_BinaryTreeMaxDistance {
    private static class Node {
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    private static class NodeInfo {
        int height;
        int maxDistance;

        public NodeInfo(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    NodeInfo maxDistance(Node x) {
        if (x == null) {
            return new NodeInfo(0, 0);
        }
        // 向左子树要信息
        NodeInfo leftInfo = maxDistance(x.left);
        // 向右子树要信息
        NodeInfo rightInfo = maxDistance(x.right);
        // 整合左右子树的信息，并设置x节点的信息
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = 0;
        maxDistance = Math.max(leftInfo.maxDistance, rightInfo.maxDistance); // 和x节点无关情况
        maxDistance = Math.max(maxDistance, leftInfo.height + rightInfo.height + 1); // 和x节点有关
        return new NodeInfo(height, maxDistance);
    }
    @Test
    void testMaxDistance() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.right = new Node(3);
        root.right.right = new Node(5);
        root.right.left = new Node(4);
        root.right.left.left = new Node(7);
        NodeInfo nodeInfo = maxDistance(root);
        Assertions.assertEquals(6, nodeInfo.maxDistance);
    }
}
