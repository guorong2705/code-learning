package com.guorong.class_02;

import com.guorong.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1-单链表反转
 */
class SinglyLinkedListReverse {

    // 反转单链表, 返回反转后的头节点
    private static SingleNode reverseLinkedList(SingleNode head) {
        SingleNode pre = null;
        SingleNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        int maxLen = 10000;
        int maxValue = 10;
        int testCount = 10000;
        for (int j = 0; j < testCount; j++) {
            // 生成随机数组
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            // 数组转换为单链表
            SingleNode head = arrayToLinkedList(array);
            // 反转单链表
            head = reverseLinkedList(head);
            // 转换链表到数组
            int[] reverseArray = linkedListToArray(head);
            // 预期结果数组
            int[] expectedArray = new int[array.length];
            int idx = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                expectedArray[idx++] = array[i];
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

    // 数组转换为单链表
    private static SingleNode arrayToLinkedList(int[] array) {
        SingleNode head = null;
        SingleNode tail = null;
        for (int value : array) {
            SingleNode node = new SingleNode(value);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }
        return head;
    }

    // 转换链表到数组
    private static int[] linkedListToArray(SingleNode head) {
        List<Integer> list = new ArrayList<>();
        // 保存元素数组
        while (head != null) {
            list.add(head.value);
            head = head.next;
        }
        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    // 单链表节点
    private static class SingleNode {
        int value;
        SingleNode next;
        public SingleNode(int value) {
            this.value = value;
        }
    }
}
