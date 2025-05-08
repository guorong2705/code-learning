package com.guorong.class_07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 二叉树的序列化与反序列化实现。
 * 使用前序遍历将二叉树序列化为字符串，并支持从字符串反序列化回二叉树。
 */
public class BinaryTreeSerialize {
    /**
     * 二叉树节点定义。
     */
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 序列化：将二叉树转换为字符串。
     * 采用前序遍历（根 -> 左 -> 右），节点值以逗号分隔，空节点用 "null" 表示。
     *
     * @param root 二叉树根节点
     * @return 序列化后的字符串
     */
    String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        // 移除末尾逗号
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 序列化辅助方法，递归处理节点。
     *
     * @param root 当前节点
     * @param sb   用于构建字符串的 StringBuilder
     */
    private void serializeHelper(Node root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return;
        }
        sb.append(root.value).append(",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }


    /**
     * 反序列化：将字符串转换回二叉树。
     * 解析逗号分隔的字符串，按照前序遍历顺序重建树。
     *
     * @param data 序列化字符串
     * @return 重建的二叉树根节点
     * @throws IllegalArgumentException 如果输入字符串格式无效
     */
    Node deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        List<String> nodes = new ArrayList<>(Arrays.asList(data.split(",")));
        if (nodes.isEmpty() || nodes.get(0).isEmpty()) {
            return null;
        }
        return deserializeHelper(nodes);
    }
    /**
     * 反序列化辅助方法，递归重建二叉树。
     *
     * @param nodes 节点值列表
     * @return 当前子树的根节点
     */
    private Node deserializeHelper(List<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String value = nodes.remove(0);
        if (Objects.equals(value, "null")) {
            return null;
        }
        Node root = new Node(Integer.parseInt(value));
        root.left = deserializeHelper(nodes);
        root.right = deserializeHelper(nodes);
        return root;
    }
    // ==========================================================
    /**
     * 判断两棵二叉树是否相同，用于测试。
     *
     * @param n1 第一棵树的根节点
     * @param n2 第二棵树的根节点
     * @return 如果两棵树相同返回 true，否则返回 false
     */
    private boolean isSameTree(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        return n1.value == n2.value &&
                isSameTree(n1.left, n2.left) &&
                isSameTree(n1.right, n2.right);
    }
    /**
     * 测试序列化和反序列化的正确性。
     * 构建一棵测试树，序列化后反序列化，并验证结果是否一致。
     */
    @Test
    void testSerializeAndDeserialize() {
        // 构建测试树：
        //       1
        //      / \
        //     2   3
        //    / \ / \
        //   4  5 6  7
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // 序列化
        String serialized = serialize(root);
        System.out.println("Serialized string: " + serialized);

        // 反序列化
        Node deserialized = deserialize(serialized);

        // 验证
        boolean isCorrect = isSameTree(root, deserialized);
        Assertions.assertTrue(isCorrect, "测试不通过");

        // 测试空树
        serialized = serialize(null);
        deserialized = deserialize(serialized);
        Assertions.assertNull(deserialized, "测试不通过");
    }
}
