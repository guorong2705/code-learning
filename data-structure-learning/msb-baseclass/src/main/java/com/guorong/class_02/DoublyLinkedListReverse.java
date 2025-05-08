package com.guorong.class_02;

import com.guorong.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1-双向链表反转
 */
class DoublyLinkedListReverse {

    // 反转双向链表
    private static DoubleNode reverseLinkedList(DoubleNode head) {
        DoubleNode prev = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            head.prev = next;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        int maxLen = 5;
        int maxValue = 100;
        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            // 创建随机数组
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            // 数组转为双向链表
            DoubleNode head = arrayToDoublyLinkedList(array);
            // 反转双向链表
            head = reverseLinkedList(head);
            // 转换为数组
            int[] reverseArray = doublyLinkedListToArray(head);
            // 预期数组
            int[] expectedArray = new int[array.length * 2];
            int idx = 0;
            // 反序存值
            for (int j = array.length - 1; j >= 0; j--) {
                expectedArray[idx++] = array[j];
            }
            // 正序存值
            for (int j = 0; j < array.length; j++) {
                expectedArray[idx++] = array[j];
            }
            // 验证结果
            if (!Arrays.equals(expectedArray, reverseArray)) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("反转数据：" + Arrays.toString(reverseArray));
                System.out.println("预期数据：" + Arrays.toString(expectedArray));
                return;
            }
        }
    }

    // 双向链表转换为数组，先next向下遍历，然后再prev线上遍历
    private static int[] doublyLinkedListToArray(DoubleNode head) {
        if (head == null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        list.add(head.value);
        while (head.next != null) {
            head = head.next;
            list.add(head.value);
        }
        list.add(head.value);
        while (head.prev != null) {
            head = head.prev;
            list.add(head.value);
        }
        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    // 数组转换为双向链表
    private static DoubleNode arrayToDoublyLinkedList(int[] array) {
        DoubleNode head = null;
        DoubleNode tail = null;
        for (int value : array) {
            DoubleNode node = new DoubleNode(value);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
        }
        return head;
    }

    // 双向链表
    private static class DoubleNode {
        int value;
        DoubleNode prev;
        DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }
}
