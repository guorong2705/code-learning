package com.guorong.class_06;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * 常见面试题：
 * 将单向链表按某个值分成左边小、中间相等、右边大的形式。
 * 1）把链表放入数组里，在数组上做partition (笔试用)
 * 2）分成小、中、大三部分，再把各个部分之间串起来 (面试用)
 */
public class LinkedListSmallEqualBig {

    private class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 判断结果是否符合题目要求
    private boolean isMatchTopic(Node head, int pivot) {
        // 判断结果是否正确
        Stack<Integer> stack = new Stack<>();
        int lessFlag = 1; // 小于标记
        int equalFlag = 2; // 等于标记
        int greaterFlag = 3; // 大于标记
        Node currNode = head;
        while (currNode != null) {
            if (currNode.value < pivot && (stack.isEmpty() || stack.peek() != lessFlag)) {
                stack.push(lessFlag);
            } else if (currNode.value == pivot && (stack.isEmpty() || stack.peek() != equalFlag)) {
                stack.push(equalFlag);
            } else if (currNode.value > pivot && (stack.isEmpty() || stack.peek() != greaterFlag)) {
                stack.push(greaterFlag);
            }
            currNode = currNode.next;
        }
        return stack.size() <= 3;
    }


    // 1. 使用数组：将单向链表按某个值分成左边小、中间相等、右边大的形式。
    Node smallEqualBigByArray(Node head, int pivot) {
        // 没有元素，获取一个元素的情况下
        if (head == null || head.next == null) {
            return head;
        }
        // 计算元素个数
        int size = 0;
        Node currNode = head;
        while (currNode != null) {
            size++;
            currNode = currNode.next;
        }
        // 接元素存入数组
        currNode = head;
        Node[] nodeArray = new Node[size];
        for (int i = 0; i < size; i++) {
            nodeArray[i] = currNode;
            currNode = currNode.next;
        }
        // 使用partition 调整位置
        partition(nodeArray, pivot);
        // 重新链接链表
        for (int i = 0; i < nodeArray.length; i++) {
            nodeArray[i].next = null;
            if (i == 0) {
                head = nodeArray[i];
                currNode = head;
            } else {
                currNode.next = nodeArray[i];
                currNode = currNode.next;
            }
        }
        return head;
    }

    // 调整位置到将pivot放入正确的位置
    private void partition(Node[] nodeArray, int pivot) {
        int size = nodeArray.length;
        int idx = -1;
        int left = 0;
        int right = size - 1;
        // 移动小于pivot的
        for (int i = left; i <= right; i++) {
            if (nodeArray[i].value < pivot) {
                idx++;
                Node temp = nodeArray[idx];
                nodeArray[idx] = nodeArray[i];
                nodeArray[i] = temp;
            }
        }
        left = idx + 1;
        // 移动等于pivot的，从idx位置开始
        for (int i = left; i <= right; i++) {
            if (nodeArray[i].value == pivot) {
                idx++;
                Node temp = nodeArray[idx];
                nodeArray[idx] = nodeArray[i];
                nodeArray[i] = temp;
            }
        }
    }

    @Test
    public void testSmallEqualBigByArray() {
        int maxLen = 10;
        int maxValue = 10;
        int testCount = 10000 * 100;
        for (int count = 0; count < testCount; count++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            Node head = getNode(array);
            int pivot = (int) (Math.random() * (1 + maxValue));
            head = smallEqualBigByArray(head, pivot);
            if (!isMatchTopic(head, pivot)) {
                System.out.println("原始节点数据:" + Arrays.toString(array));
            }
        }
    }

    private Node getNode(int[] array) {
        Node head = null;
        Node tail = null;
        for (int i = 0; i < array.length; i++) {
            Node newNode = new Node(array[i]);
            if (i == 0) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        return head;
    }


    // ========================================================================
    // 2. 分成小、中、大三部分，再把各个部分之间串起来 (面试用)
    Node smallEqualBig(Node head, int pivot) {
        // 存在一个字情况
        if (head == null || head.next == null) {
            return head;
        }
        // 小于部分头尾指针
        Node lessHead = null;
        Node lessTail = null;
        // 等于部分头尾指针
        Node equalHead = null;
        Node equalTail = null;
        // 大于部分头尾指针
        Node greaterHead = null;
        Node greaterTail = null;
        // 遍历原链表，按值分类
        while (head != null) {
            Node next = head.next; // 存储下一个指针
            head.next = null; // 断开原链表，防止脏链
            if (head.value < pivot) {
                if (lessHead == null) {
                    lessHead = head;
                } else {
                    lessTail.next = head;
                }
                lessTail = head;
            }
            // 等于
            else if (head.value == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                } else {
                    equalTail.next = head;
                }
                equalTail = head;
            }
            // 大于
            else {
                if (greaterHead == null) {
                    greaterHead = head;
                } else {
                    greaterTail.next = head;
                }
                greaterTail = head;
            }
            head = next; // 移动头指针到下一个节点
        }
        // 连接三部分链表
        Node newHead = null;
        // less-> equal
        if (lessTail != null) {
            newHead = lessHead;
            lessTail.next = equalHead;
        } else {
            newHead = equalHead != null ? equalHead : greaterHead;
        }
        // equals -> greater
        if (equalTail != null) {
            equalTail.next = greaterHead;
        }
        return newHead;
    }

    @Test
    public void testSmallEqualBig() {
        int maxLen = 10;
        int maxValue = 10;
        int testCount = 10000 * 100;
        for (int count = 0; count < testCount; count++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            Node head = getNode(array);
            int pivot = (int) (Math.random() * (1 + maxValue));
            head = smallEqualBig(head, pivot);
            if (!isMatchTopic(head, pivot)) {
                System.out.println("原始节点数据:" + Arrays.toString(array));
            }
        }
    }


}
