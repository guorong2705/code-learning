package com.guorong.class_02;

import com.guorong.ArrayUtils;

import java.util.Arrays;

/**
 * 使用链表实现队列
 */
class QueueLinkedList {
    public static void main(String[] args) {
        int maxLen = 1000;
        int maxValue = 100;
        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            // 生成随机数组
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            // 将随机数组入队
            MyQueue queue = new MyQueue();
            for (int value : array) {
                queue.enqueue(value);
            }
            // 将数组出队，并组装成数组
            int[] resultArray = new int[array.length];
            int idx = 0;
            while (!queue.isEmpty()) {
                resultArray[idx++] = queue.dequeue();
            }
            // 验证输入数据和出队数据是否相同：FIFO
            if (!Arrays.equals(array, resultArray)) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("出队数据：" + Arrays.toString(resultArray));
                return;
            }
        }
    }


    // 队列实现
    private static class MyQueue {
        private Node head;
        private Node tail;
        private int size;

        public MyQueue() {
            this.size = 0;
            this.head = null;
            this.tail = null;
        }

        // 入队
        public void enqueue(int element) {
            Node node = new Node(element);
            // 队列为空
            if (isEmpty()) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
            size++;
        }

        // 出队
        public int dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("queue is null");
            }
            Node node = head;
            head = head.next;
            size--;
            return node.value;
        }

        // 队列是否为空
        public boolean isEmpty() {
            return head == null;
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
