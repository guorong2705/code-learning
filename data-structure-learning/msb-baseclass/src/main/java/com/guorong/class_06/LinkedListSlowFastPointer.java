package com.guorong.class_06;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 快慢指针
 * 链表快慢指针应用
 */
class LinkedListSlowFastPointer {

    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 1.输入链表头节点，奇数长度返回中点，偶数长度返回上中点。
    Node midUp(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        // 走到此处链表拥有三个或以上的节点
        Node slow = head.next; // 慢指针
        Node fast = head.next.next; // 快指针
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Test
    void testMidOrMid() {
        int maxLen = 100;
        int maxValue = 100;
        int testCount = 10000 * 100;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            Node head = null;
            Node tail = null;
            for (int j = 0; j < array.length; j++) {
                Node newNode = new Node(array[j]);
                if (j == 0) {
                    head = newNode;
                } else {
                    tail.next = newNode;
                }
                tail = newNode;
            }
            Node node = midUp(head);
            int mid = (array.length - 1) / 2;
            boolean isError = true;
            // 情况判断
            if (array.length > 0 && node != null && array[mid] == node.value) {
                isError = false;
            } else if (array.length == 0 && node == null) {
                isError = false;
            }
            if (isError) {
                System.out.println("错误数组:" + Arrays.toString(array));
            }
        }
    }


    //===================================================================
    // 2.输入表头节点，奇数长度返回中点，偶数长度返回下中点
    Node midDown(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 两个及两个以上元素
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Test
    void testMidDown() {
        int maxLen = 6;
        int maxValue = 100;
        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            // int[] array = {95, 9, 41, 89, 4};
            Node head = null;
            Node tail = null;
            for (int j = 0; j < array.length; j++) {
                Node newNode = new Node(array[j]);
                if (j == 0) {
                    head = newNode;
                } else {
                    tail.next = newNode;
                }
                tail = newNode;
            }
            Node node = midDown(head);
            int mid = (array.length - 1) / 2;
            if (array.length % 2 == 0) {
                mid = mid + 1;
            }
            boolean isError = true;
            // 情况判断
            if (array.length > 0 && node != null && array[mid] == node.value) {
                isError = false;
            } else if (array.length == 0 && node == null) {
                isError = false;
            }
            if (isError) {
                System.out.println("错误数组:" + Arrays.toString(array));
            }
        }
    }


    // ========================================================================
    // todo 3.输入链表头节点，奇数长度返回中点前一个，偶数长度返回上一个中点前一个
    void mid3(Node head) {

    }


    // ========================================================================
    // todo 4.输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    void mid5(Node head) {

    }
}
