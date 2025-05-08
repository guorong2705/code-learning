package com.guorong.class_02;

import com.guorong.ArrayUtils;

import java.util.Arrays;

/**
 * 单链表实现栈
 */
class StackLinkedList {

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 10;
        int testCount = 100;
        for (int i = 0; i < testCount; i++) {
            // 生成随机数组
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            // 将数组数据压入栈中
            Stack stack = new Stack();
            for (int value : array) {
                stack.push(value);
            }
            // 从栈弹出数据
            int[] popStackArray = new int[array.length];
            for (int j = 0; j < popStackArray.length; j++) {
                if (!stack.isEmpty()) {
                    popStackArray[j] = stack.pop();
                }
            }
            // 生成预期数组
            int[] expectedArray = new int[array.length];
            int idx = 0;
            for (int j = array.length - 1; j >= 0; j--) {
                expectedArray[idx++] = array[j];
            }
            // 验证
            if (!Arrays.equals(expectedArray, popStackArray)) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("弹栈数据：" + Arrays.toString(popStackArray));
                System.out.println("预期数据：" + Arrays.toString(expectedArray));
                return;
            }
        }


    }


    // 栈
    private static class Stack {
        private Node head;

        public Stack() {
        }

        // 使用头插法
        public void push(int element) {
            Node node = new Node(element);
            if (head == null) {
                head = node;
            } else {
                node.next = head;
                head = node;
            }
        }

        public boolean isEmpty() {
            return head == null;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            Node node = head;
            head = head.next;
            return node.value;
        }

        // 节点
        private static class Node {
            int value;
            Node next;
            public Node(int value) {
                this.value = value;
            }
        }
    }


}
