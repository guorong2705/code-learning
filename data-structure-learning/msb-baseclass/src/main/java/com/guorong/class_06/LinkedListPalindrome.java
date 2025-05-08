package com.guorong.class_06;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * 常见面试题：关于链表的
 * 给定一个单链表的头节点head，请判断该链表是否为回文结构
 * 1）栈方法特别简单(笔试用)
 * 2）改原链表的方法就需要注意边界了(面试用)
 */
class LinkedListPalindrome {

    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    // 1.使用栈结构来判断链表是否为回文（空间额外复杂度为 O(n)）
    boolean isPalindromeByStack(Node head) {
        if (head == null) {
            return false;
        }
        // 使用栈帧存储链表数据
        Stack<Node> stack = new Stack<>();
        Node currNode = head;
        while (currNode != null) {
            stack.push(currNode);
            currNode = currNode.next;
        }
        // 从栈中取出数据和原始链表比较，如果都相同，就是回文
        currNode = head;
        while (currNode != null) {
            if (currNode.data != stack.pop().data) {
                return false;
            }
            currNode = currNode.next;
        }
        return true;
    }

    @Test
    void testPalindromeNumberByStack() {
        int maxLen = 10;
        int maxValue = 3;
        int testCount = 10000 * 10;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            Node head = null;
            Node tail = null;
            // 填充元素
            for (int index = 0; index < array.length; index++) {
                Node newNode = new Node(array[index]);
                if (index == 0) {
                    head = newNode;
                    tail = newNode;
                } else {
                    tail.next = newNode;
                    tail = newNode;
                }
            }
            // 判断结构
            if (ArrayUtils.isPalindromeNum(array) != isPalindromeByStack(head)) {
                System.out.println("错误数据:" + Arrays.toString(array));
            }
        }
    }

    // ===========================================================================
    // 2.反转后半部分链表方法
    boolean isPalindromeByReverse(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 找到中点（使用快慢指针）
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;
        Node rightHead = reverseList(mid.next);
        Node leftHead = head;
        while (rightHead != null) {
            if (leftHead.data != rightHead.data) {
                return false;
            }
            rightHead = rightHead.next;
            leftHead = leftHead.next;
        }
        return true;
    }

    private Node reverseList(Node head) {
        Node pre = null;
        Node current = head;
        while (current != null) {
            Node tempNext = current.next;
            current.next = pre;
            pre = current;
            current = tempNext;
        }
        // 反转反转后的头指针
        return pre;
    }

    @Test
    void testIsPalindromeNumByReverse() {
        int maxLen = 6;
        int maxValue = 3;
        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            Node head = null;
            Node tail = null;
            for (int idx = 0; idx < array.length; idx++) {
                Node newNode = new Node(array[idx]);
                if (idx == 0) {
                    head = newNode;
                } else {
                    tail.next = newNode;
                }
                tail = newNode;
            }
            if (isPalindromeByReverse(head) != ArrayUtils.isPalindromeNum(array)) {
                System.out.println("数组:" + Arrays.toString(array));
            }
        }
    }


}
