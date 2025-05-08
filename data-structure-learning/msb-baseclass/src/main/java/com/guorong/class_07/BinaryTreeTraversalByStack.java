package com.guorong.class_07;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * 二叉树遍历使用栈（先序、中序、后续）
 */
public class BinaryTreeTraversalByStack {
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 使用栈实现二叉树的先序遍历（根-左-右）。
     * 通过栈存储节点，确保先访问根节点，再依次处理左子树和右子树。
     * 时间复杂度：O(n)，空间复杂度：O(h)，其中 n 是节点数，h 是树高。
     * @param root 二叉树的根节点
     */
    void preOrder(Node root) {
        // 空树直接返回
        if (root == null) {
            return;
        }
        // 初始化栈，用于存储待处理的节点
        Stack<Node> stack = new Stack<>();
        // 将根节点入栈，开始遍历
        stack.push(root);
        // 循环直到栈为空
        while (!stack.isEmpty()) {
            // 弹出栈顶节点并访问其值
            Node node = stack.pop();
            System.out.print(node.value + " ");
            // 先将右子节点入栈（后处理）
            if (node.right != null) {
                stack.push(node.right);
            }
            // 再将左子节点入栈（先处理，栈是LIFO）
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
    @Test
    void testPreOrder() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        preOrder(root); // 1 2 4 5 3
    }

    // =======================================================

    /**
     * 使用栈实现二叉树的中序遍历（左-根-右）。
     * 通过栈存储节点，确保先访问左子树，再访问根节点，最后访问右子树。
     * 时间复杂度：O(n)，空间复杂度：O(h)，其中 n 是节点数，h 是树高。
     *
     * @param root 二叉树的根节点
     */
    void inOrder(Node root) {
        // 空树直接返回
        if (root == null) {
            return;
        }
        // 初始化栈，用于存储待处理的节点
        Stack<Node> stack = new Stack<>();
        // 当前节点指针，初始指向根节点
        Node curr = root;
        // 循环直到当前节点为空且栈为空
        while (curr != null || !stack.isEmpty()) {
            // 将当前节点及其所有左子节点入栈
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // 弹出栈顶节点，访问其值
            curr = stack.pop();
            System.out.print(curr.value + " ");
            // 转向右子树，继续处理
            curr = curr.right;
        }
    }

    @Test
    void testInOrder() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        inOrder(root); // 预期输出：4 2 5 1 3
    }
    // =======================================================

    // 后续遍历：使用两个栈
    void postOrder(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        // 第一步：按"根-右-左"顺序将节点放入stack2
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            stack2.push(node);
            // 放入左指针
            if (node.left != null) {
                stack1.push(node.left);
            }
            // 放入右指针
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        // 第二步：从stack2弹出，得到"左-右-根"顺序
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value + " ");
        }
    }

    @Test
    void testPostOrder() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        postOrder(root); // 预期输出：4 5 2 3 1
    }

    // =======================================================

    /**
     * 使用单个栈实现二叉树的后序遍历（左-右-根）。
     * 通过跟踪最后访问的节点（lastVisited）来确定是否可以访问当前节点。
     * 时间复杂度：O(n)，空间复杂度：O(h)，其中 n 是节点数，h 是树高。
     *
     * @param root 二叉树的根节点
     */
    void postOrder2(Node root) {
        // 空树直接返回
        if (root == null) {
            return;
        }
        // 初始化栈，用于存储待处理的节点
        Stack<Node> stack = new Stack<>();
        // 跟踪最后访问的节点，初始为根节点
        Node lastVisited = null;
        // 将根节点入栈，开始遍历
        stack.push(root);

        // 循环直到栈为空
        while (!stack.isEmpty()) {
            // 查看栈顶节点，但不弹出
            Node node = stack.peek();

            // 情况1：如果存在左子节点，且左右子节点都未访问，将左子节点入栈
            if (node.left != null && node.left != lastVisited && node.right != lastVisited) {
                stack.push(node.left);
            }
            // 情况2：如果存在右子节点，且右子节点未访问，将右子节点入栈
            else if (node.right != null && node.right != lastVisited) {
                stack.push(node.right);
            }
            // 情况3：左右子节点都已访问或不存在，访问当前节点
            else {
                // 弹出并访问当前节点
                System.out.print(stack.pop().value + " ");
                // 更新最后访问的节点
                lastVisited = node;
            }
        }
    }

    @Test
    void testPostOrder2() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        postOrder2(root); // 预期输出：4 5 2 3 1
    }


}










