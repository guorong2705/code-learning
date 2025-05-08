package com.guorong.sort;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * 计数排序（Counting Sort）
 * 是一种高效的非比较排序算法，适用于非负整数且数值范围较小的场景。
 * 它通过统计每个元素出现的次数，直接构造排序后的结果，而不是通过比较元素大小。
 */
class ArraySort_07_CountingSort extends ArraySort {

    @Test
    void testCountSort() {
        Consumer<int[]> countSort = new CountSort()::countingSort;
        new ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, countSort);
    }

    private static class CountSort {
        /**
         * 数组 array（包含 n 个非负整数），最大值 max
         */
        public void countingSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            // 找出最大的值
            int max = Integer.MIN_VALUE;
            for (int value : array) {
                if (value<0){
                    throw new IllegalArgumentException("无法排序负数");
                }
                max = Math.max(max, value);
            }
            // 基数统计
            int[] countArray = new int[max + 1];
            for (int num : array) {
                countArray[num]++; // 计数数组索引值作为出现的数
            }
            // 将计数数组数据回填到原数组中
            int idx = 0;
            for (int num = 0; num < countArray.length; num++) {
                int count = countArray[num]; // num数字出现次数
                for (int j = 0; j < count; j++) {
                    array[idx++] = num;
                }
            }
        }
    }


}
