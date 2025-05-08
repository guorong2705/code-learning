package com.guorong.class_02;

import com.guorong.ArrayUtils;
import sun.plugin2.gluegen.runtime.CPU;

import java.util.Arrays;

/**
 * 使用数组实现队列
 */
class QueueArray {
    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 100;
        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            MyQueue queue = new MyQueue();
            for (int value : array) {
                queue.enqueue(value);
            }
            int[] queueArray = new int[array.length];
            int idx = 0;
            while (!queue.isEmpty()) {
                queueArray[idx++] = queue.dequeue();
            }
            // 验证结果
            if (!Arrays.equals(array, queueArray)) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("队列数据：" + Arrays.toString(queueArray));
                return;
            }

        }
    }


    // 使用数组实现队列
    private static class MyQueue {
        private static final int DEFAULT_CAPACITY = 100;
        private final int[] data;
        // 队列容量
        private final int capacity;
        private int size;
        // 头指针
        private int front;
        // 尾指针
        private int rear;

        public MyQueue() {
            this(DEFAULT_CAPACITY);
        }

        public MyQueue(int capacity) {
            if (capacity < DEFAULT_CAPACITY) {
                capacity = DEFAULT_CAPACITY;
            }
            this.data = new int[capacity];
            this.size = 0;
            this.capacity = capacity;
            this.front = 0;
            this.rear = 0;
        }

        private boolean isFull() {
            return size >= capacity;
        }

        private boolean isEmpty() {
            return size == 0;
        }

        public void enqueue(int element) {
            if (isFull()) {
                throw new IllegalStateException("queue is full");
            }
            size--;
            data[rear] = element;
            rear = nextIndex(rear);
        }

        public int dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("queue is empty");
            }
            size++;
            int value = data[front];
            front = nextIndex(front);
            return value;
        }

        // 下一个索引
        private int nextIndex(int index) {
            return index < capacity - 1 ? index + 1 : 0;
        }
    }
}
