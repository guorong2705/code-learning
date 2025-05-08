package com.guorong.class_02;

import com.guorong.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2.单链表删除指定的值
 */
class SinglyLinkedListRemoveNum {

    // 单向链表删除指定值
    private static SingleNode linkedListRemove(SingleNode head, int num) {
        // 将头指针移动到非删除节点
        while (head != null && head.value == num) {
            head = head.next;
        }
        // 删除头节点的后续节点
        SingleNode prev = head;
        SingleNode current = head;
        while (current != null) {
            if (current.value == num) {
                prev.next = current.next;
            } else {
                prev = current;
            }
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int maxLen = 1000;
        int maxValue = 30;
        int testCount = 10000 * 10;
        for (int i = 0; i < testCount; i++) {
            // 创建随机数组
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            int random = ArrayUtils.generateRandInt(maxValue);
            // 随机数组转换为单链表
            SingleNode head = arrayToLinkedList(array);
            // 删除节点
            head = linkedListRemove(head, random);
            int[] removeArray = linkedListToArray(head);
            // 参照数组
            int[] expectedArray = Arrays.copyOf(array, array.length);
            expectedArray = arrayRemove(expectedArray, random);
            // 验证
            if (!Arrays.equals(expectedArray, removeArray)) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("删除数据：" + Arrays.toString(removeArray));
                System.out.println("参照数据：" + Arrays.toString(expectedArray));
                return;
            }
        }
    }

    // 数组转换为单向链表
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

    // 单向链表转换为数组
    private static int[] linkedListToArray(SingleNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.value);
            head = head.next;
        }
        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static int[] arrayRemove(int[] array, int num) {
        return Arrays.stream(array)
                .filter(e -> num != e)
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
