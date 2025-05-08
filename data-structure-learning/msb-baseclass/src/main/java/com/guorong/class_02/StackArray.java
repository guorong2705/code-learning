package com.guorong.class_02;

import com.guorong.ArrayUtils;

import java.util.Arrays;

/**
 * 使用数组实现栈
 */
class StackArray {

    public static void main(String[] args) {
        int maxLen = 100000000;
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
        private static final int DEFAULT_CAPACITY = 100;
        private int[] data;
        private int size;
        private int capacity;

        private boolean isInit = false;

        public Stack() {
            this(DEFAULT_CAPACITY);
        }

        public Stack(int capacity) {
            if (capacity > DEFAULT_CAPACITY) {
                capacity = DEFAULT_CAPACITY;
            }
            this.data = new int[capacity];
            this.size = 0;
            this.capacity = capacity;
            this.isInit = true;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 入栈
        public void push(int element) {
            if (size == capacity) {
                throw new RuntimeException("stack is full");
            }
            if (!isInit) {
                throw new IllegalStateException("stack is not init");
            }
            data[size++] = element;
        }

        // 出栈
        public int pop() {
            if (!isInit) {
                throw new IllegalStateException("stack is not init");
            }
            if (isEmpty()) {
                throw new IllegalStateException("stack is empty");
            }
            return data[--size];
        }

    }
}
