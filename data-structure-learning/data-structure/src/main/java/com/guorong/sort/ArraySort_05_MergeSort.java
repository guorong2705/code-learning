package com.guorong.sort;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * 归并排序
 */
class ArraySort_05_MergeSort extends ArraySort {

    @Test
    void testStandardMergeSort() {
        Consumer<int[]> mergeSort = new StandardMergeSort()::mergeSort;
        new ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, mergeSort);
    }


    /**
     * 经典归并排序
     */
    private static class StandardMergeSort {
        // 归并排序
        public void mergeSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            divide(array, 0, array.length - 1);
        }

        // 切分数组
        private void divide(int[] array, int left, int right) {
            if (left < right) {
                int mid = left + ((right - left) >> 1);
                divide(array, left, mid);
                divide(array, mid + 1, right);
                merge(array, left, mid, right);
            }
        }

        // 合并元素：使[lef,right]之间的数组有序
        private void merge(int[] array, int low, int mid, int high) {
            int[] tempArray = new int[high - low + 1];
            int tempIdx = 0;
            int left = low;
            int right = mid + 1;
            // 将小的存入临时数组
            while (left <= mid && right <= high) {
                if (array[left] < array[right]) {
                    tempArray[tempIdx++] = array[left++];
                } else {
                    tempArray[tempIdx++] = array[right++];
                }
            }
            // 剩余数组存入临时数组
            while (left <= mid) {
                tempArray[tempIdx++] = array[left++];
            }
            while (right <= high) {
                tempArray[tempIdx++] = array[right++];
            }
            // 复制临时数组数据
            for (int value : tempArray) {
                array[low++] = value;
            }
        }
    }

}
