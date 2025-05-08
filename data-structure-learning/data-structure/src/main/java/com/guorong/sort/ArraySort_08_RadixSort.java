package com.guorong.sort;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * 基数排序（Radix Sort）
 */
 class ArraySort_08_RadixSort extends ArraySort {


    // 基数排序（使用计数数组）
    @Test
    void testRadixSortByCountingArray() {
        Consumer<int[]> radixSort = new RadixSortByCountingArray()::radixSort;
        new ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, radixSort);
    }

    // 基数排序（使用队列）
    @Test
    void testRadixSortByQueue() {
        Consumer<int[]> radixSort = new RadixSortByQueue()::radixSort;
        new ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, radixSort);
    }


    /**
     * 标准基数排序
     */
    private static class RadixSortByQueue {
        public void radixSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            int max = Integer.MIN_VALUE;
            for (int value : array) {
                if (value < 0) {
                    throw new IllegalArgumentException("无法排序负数");
                }
                max = Math.max(max, value);
            }
            for (int exp = 1; max / exp > 0; exp *= 10) {
                radixSortByDigit(array, exp);
            }
        }

        private void radixSortByDigit(int[] array, int exp) {
            @SuppressWarnings("unchecked")
            LinkedList<Integer>[] queueArray = new LinkedList[10];
            Arrays.setAll(queueArray, i -> new LinkedList<Integer>());
            for (int value : array) {
                int digit = (value / exp) % 10;
                queueArray[digit].offer(value);
            }
            int idx = 0;
            for (Queue<Integer> queue : queueArray) {
                while (!queue.isEmpty()) {
                    array[idx++] = queue.poll();
                }
            }
        }
    }


    /**
     * 使用计数实现计数排序
     */
    private static class RadixSortByCountingArray {
        public void radixSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            // 查找数组中的最大值
            int max = Integer.MIN_VALUE;
            for (int value : array) {
                if (value < 0) {
                    throw new IllegalArgumentException("无法排序负数");
                }
                max = Math.max(value, max);
            }
            // 位数排序
            for (int exp = 1; max / exp > 0; exp *= 10) {
                countingSortByDigit(array, exp);
            }
        }

        private void countingSortByDigit(int[] array, int exp) {
            int n = array.length;
            int[] tempArray = new int[n];
            int[] countArray = new int[10];
            for (int value : array) {
                int digit = (value / exp) % 10;
                countArray[digit]++;
            }
            for (int i = 1; i < 10; i++) {
                countArray[i] += countArray[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                int digit = (array[i] / exp) % 10;
                tempArray[countArray[digit] - 1] = array[i];
                countArray[digit]--;
            }
            System.arraycopy(tempArray, 0, array, 0, n);
        }

    }
}
