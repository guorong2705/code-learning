package com.guorong.class_07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 计算二叉树最宽一层的节点数
 */
class BinaryTreeGetLevelWithMaxNodes {
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 实现二叉树的层序遍历（按层从上到下、从左到右）。
     * 使用队列存储节点，StringBuilder 收集输出，确保高效遍历和格式化。
     * 时间复杂度：O(n)，空间复杂度：O(w)，其中 n 是节点数，w 是树的最大宽度。
     *
     * @param root 二叉树的根节点
     */
    String levelOrder(Node root) {
        StringBuilder sb = new StringBuilder();
        // 空树返回
        if (root == null) {
            return sb.toString();
        }
        // 初始化队列
        Queue<Node> queue = new ArrayDeque<>();
        // 将根节点入队
        queue.offer(root);
        // 层序遍历
        while (!queue.isEmpty()) {
            // 取出队首节点并记录值
            Node node = queue.poll();
            sb.append(node.value).append(" ");
            // 将左子节点入队
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 将右子节点入队
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    @Test
    void testLevelOrder() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        String expected = "1 2 3 4 5 6 7";
        Assertions.assertEquals(expected, levelOrder(root));
    }


    // ================================================

    /**
     * 计算二叉树最宽一层的节点数（最大宽度）。
     * 使用层序遍历和 HashMap 跟踪节点层级，记录每层节点数并更新最大值。
     * 时间复杂度：O(n)，空间复杂度：O(n)，其中 n 是节点数。
     * -- 流程：
     * 1.将根节点入队，标记层级为 1。
     * 2.循环：取出节点，记录其层级，将子节点入队并标记层级。
     * 3.如果节点在当前层，计数加 1；否则，更新最大宽度，重置计数并切换层级。
     * 4.最后比较最后一层的宽度。
     *
     * @param root 二叉树的根节点
     * @return 最宽一层的节点数
     */
    int getLevelWithMaxNodes(Node root) {
        // 空树
        if (root == null) {
            return 0;
        }
        // 初始化队列和层级映射
        Queue<Node> queue = new ArrayDeque<>();
        Map<Node, Integer> node2LevelMap = new HashMap<>();
        queue.offer(root);
        node2LevelMap.put(root, 1);

        int currLevel = 1; // 当前层级
        int levelNodeCount = 0; // 当前层节点计数
        int maxWidth = 0; // 层级最大宽度

        // 按层序遍历
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int level = node2LevelMap.get(node);
            // 将左右子节点入队并记录层级
            if (node.left != null) {
                queue.offer(node.left);
                node2LevelMap.put(node.left, level + 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                node2LevelMap.put(node.right, level + 1);
            }

            // 处理层级计数
            if (level == currLevel) {
                levelNodeCount++;
            }
            // 进入新层级，更新最大宽度并重置层级计数
            else {
                maxWidth = Math.max(maxWidth, levelNodeCount);
                levelNodeCount = 1;
                currLevel = level;
            }
        }
        // 比较最后一层的宽度
        return Math.max(maxWidth, levelNodeCount);
    }

    @Test
    void testGetLevelWithMaxNodes() {
        // 构建测试树：
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        int maxNodes = getLevelWithMaxNodes(root);
        Assertions.assertEquals(1, maxNodes);
    }

    // =============================================================

    /**
     * 计算二叉树最宽一层的节点数（最大宽度）。
     * 使用层序遍历，逐层处理节点，记录每层节点数并更新最大值。
     * 时间复杂度：O(n)，空间复杂度：O(w)，其中 n 是节点数，w 是树的最大宽度。
     *
     * @param root 二叉树的根节点
     * @return 最宽一层的节点数
     */
    int getLevelWithMaxNodesTwo(Node root) {
        if (root == null) {
            return 0;
        }
        // 初始化队列
        Queue<Node> queue = new ArrayDeque<>();
        // 将根节点入队
        queue.offer(root);
        int maxWidth = 0;
        // 层序遍历
        while (!queue.isEmpty()) {
            // 当前层节点数
            int levelSize = queue.size();
            // 更新最大宽度
            maxWidth = Math.max(maxWidth, levelSize);
            // 处理当前层的所有节点
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                // 将左右子节点入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return maxWidth;
    }
    @Test
    void testGetLevelWithMaxNodesTwo() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        int maxWidth = getLevelWithMaxNodesTwo(root);
        Assertions.assertEquals(4, maxWidth);
    }

}
