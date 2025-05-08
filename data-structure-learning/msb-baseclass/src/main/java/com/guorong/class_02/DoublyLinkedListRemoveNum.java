package com.guorong.class_02;

import com.guorong.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 双向链表移除指定值
 */
class DoublyLinkedListRemoveNum {

    // 双向链表删除指定值
    private static DoubleNode removeNum(DoubleNode head, int num) {
        // 处理空链表
        if (head == null) {
            return null;
        }
        // 指针移动到非num处
        while (head != null && head.value == num) {
            head = head.next;
        }
        // 清空头指针前面的关联关系
        if (head != null) {
            head.prev = null;
        }
        // 删除剩余数据
        DoubleNode current = head;
        while (current != null && current.next != null) {
            if (current.next.value == num) {
                current.next = current.next.next; // 跳过一个节点
                if (current.next != null) {
                    current.next.prev = current;
                }
            } else {
                current = current.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 100;
        int testCount = 10000 * 100;
        for (int i = 0; i < testCount; i++) {
            // 生成随机数组和随机删除值
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            int random = ArrayUtils.generateRandInt(maxValue);
            // 将随机数组转换为双向链表
            DoubleNode head = array2DoubleLinkedList(array);
            // 执行删除链表值，并转换为数组
            head = removeNum(head, random);
            int[] removeArray = doubleLinkedList2Array(head);
            // 生成预期数组
            int[] tempArray = removeNum(array, random);
            int[] expectedArray = new int[2 * tempArray.length];
            int tempIdx = 0;
            for (int j = 0; j < expectedArray.length; j++) {
                if (j < tempArray.length) {
                    expectedArray[j] = tempArray[tempIdx++];
                } else {
                    expectedArray[j] = tempArray[--tempIdx];
                }
            }
            // 验证结果
            if (!Arrays.equals(expectedArray, removeArray)) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("删除数据：" + random);
                System.out.println("链表数据：" + Arrays.toString(removeArray));
                System.out.println("预期数据：" + Arrays.toString(expectedArray));
                return;
            }
        }
    }

    // 数组转换为双向链表
    private static DoubleNode array2DoubleLinkedList(int[] array) {
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

    // 双向链表转换为数组：双倍长度
    private static int[] doubleLinkedList2Array(DoubleNode head) {
        if (head == null) {
            return new int[0];
        }
        // 双倍存储到数组
        List<Integer> list = new ArrayList<>();
        DoubleNode current = head;
        list.add(current.value);
        while (current.next != null) {
            current = current.next;
            list.add(current.value);
        }
        list.add(current.value);
        while (current.prev != null) {
            current = current.prev;
            list.add(current.value);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // 暴力移除移除数组中的元素，并生成新数组
    private static int[] removeNum(int[] array, int num) {
        List<Integer> list = new ArrayList<>();
        for (int value : array) {
            if (value != num) {
                list.add(value);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }


    // 双向链表节点
    private static class DoubleNode {
        int value;
        DoubleNode prev;
        DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }
}
