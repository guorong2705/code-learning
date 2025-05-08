package com.guorong.sort;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * 快速排序
 */
class ArraySort_04_QuickSort extends ArraySort {

    /**
     * -- Lomuto分区（单向分区，以末尾元素为基准）
     */
    @Test
    void testQuickSortLomutoPartition() {
        Consumer<int[]> quickSort = new QuickSortLomutoPartition()::quickSort;
        new ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, quickSort);
    }


    /***
     * - Hoare分区
     * Hoare分区（Hoare Partition）是快速排序中一种经典且高效的分区方法，
     * 由 C.A.R. Hoare 提出。它通过从数组两端向中间扫描，交换不符合条件的元素来实现分区。
     */
    @Test
    void testQuickSortHoarePartition() {
        Consumer<int[]> quickSort = new QuickSortHoarePartition()::quickSort;
        new ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, quickSort);
    }

    /**
     * Hoare分区：从两端扫描，适合处理重复元素，减少不必要交换。
     */
    private static class QuickSortHoarePartition {
        /**
         * 快速排序
         *
         * @param array
         */
        void quickSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            quickSortRec(array, 0, array.length - 1);
        }

        // 快速排序递归
        void quickSortRec(int[] array, int low, int high) {
            if (low < high) {
                int pivotIdx = hoarePartition(array, low, high);
                quickSortRec(array, low, pivotIdx);
                quickSortRec(array, pivotIdx + 1, high);
            }
        }

        // hoare分区结束后：返回分区边界，而不是基准的最终位置。。
        private int hoarePartition(int[] array, int low, int high) {
            int pivot = array[low];
            int left = low;
            int right = high;
            while (true) {
                while (array[left] < pivot) {
                    left++;
                }
                while (array[right] > pivot) {
                    right--;
                }
                if (left >= right) {
                    return right;
                }
                // 交换并移动left和right指针后，使left左侧小于等于pivot，right右侧大于等于pivot
                ArrayUtils.swap(array, left, right);
                left++;
                right--;
            }
        }
    }

    // ===============================================

    /**
     * -- 基本快速排序（单向分区，以末尾元素为基准）
     * 特点：以最后一个元素为枢轴，单向扫描分区，小于等于枢轴的元素移到左边。
     * 实现要点：简单，代码直观，但对已排序或逆序数组性能较差（O(n²) 最坏情况）。
     * 适用场景：教学或小型数据集。
     */
    private static class QuickSortLomutoPartition {
        // 快速排序
        public void quickSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            quickSortRec(array, 0, array.length - 1);
        }

        /**
         * 快速排序递归
         */
        private void quickSortRec(int[] array, int left, int right) {
            if (left < right) {
                int pivotIdx = partition(array, left, right);
                quickSortRec(array, left, pivotIdx - 1);
                quickSortRec(array, pivotIdx + 1, right);
            }
        }

        /**
         * -- Lomuto 分区
         * 在 [left, right] 范围内将数组分成两部分：左侧为小于等于枢轴值的元素，
         * 右侧为大于枢轴的元素。使用最后一个元素 array[right] 作为枢轴值。
         *
         * @return 基准值的最终索引
         */
        private int partition(int[] array, int left, int right) {
            int pivotValue = array[right];
            // 表示小于等于基准区域的右边界。
            int pivotPrevIdx = left - 1;
            // 遍历 [left, right-1] 范围内的元素，排除枢轴值 array[right]
            for (int i = left; i < right; i++) {
                if (array[i] <= pivotValue) {
                    pivotPrevIdx++;
                    ArrayUtils.swap(array, pivotPrevIdx, i);
                }
            }
            // 将枢轴放到最终位置
            ArrayUtils.swap(array, pivotPrevIdx + 1, right);
            return pivotPrevIdx + 1;
        }
    }
}
