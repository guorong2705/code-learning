package com.guorong.sort;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * 直接插入排序
 */
class ArraySort_02_InsertitionSort extends ArraySort {

    /**
     * 标准插入排序
     */
    @Test
    void test01StandardInsertionSort() {
        Consumer<int[]> insertionSort = new StandardInsertionSort()::insertionSort;
        new ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, insertionSort);
    }

    /**
     * 二分插入排序
     */
    @Test
    void test02BinaryInsertionSort() {
        Consumer<int[]> insertionSort = new BinaryInsertionSort()::insertionSort;
        new ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, insertionSort);
    }

    /**
     * 希尔插入排序
     */
    @Test
    void test03ShellInsertionSort() {
        Consumer<int[]> insertionSort = new ShellInsertionSort()::insertionSort;
        new ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, insertionSort);
    }

    /**
     * 标准插入排序
     */
    private static class StandardInsertionSort {
        public void insertionSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            for (int i = 1; i < array.length; i++) {
                int value = array[i]; // 存储插入值
                int idx = i - 1;
                while (idx >= 0 && value < array[idx]) {
                    array[idx + 1] = array[idx]; // 后移元素
                    idx--;
                }
                array[idx + 1] = value;
            }
        }
    }

    /**
     * 二分插入排序
     */
    private static class BinaryInsertionSort {
        public void insertionSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            int n = array.length;
            for (int i = 1; i < n; i++) { // 从索引1开始插入开始插入前面有序部分
                int insertValue = array[i]; // 临时存储插入值
                // 二分比较：将指针left指向大于插入值的起始位置
                int left = 0;
                int right = i - 1;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (insertValue < array[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                // 移动大于插入值的位置，left开始都是大于insertValue的值
                for (int j = i - 1; j >= left; j--) {
                    array[j + 1] = array[j];
                }
                array[left] = insertValue;
            }
        }
    }

    /**
     * 希尔排序（分组插入排序）
     */
    private static class ShellInsertionSort {
        public void insertionSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            int n = array.length;
            for (int gap = n / 2; gap >= 1; gap = gap / 2) { // 分组间隙，gap=1时候就是直接插入排序
                for (int i = gap; i < n; i++) {

                    int insertValue = array[i]; // 临时存储插入值
                    int insertIdx = i; // 插入位置

                    for (int j = i - gap; j >= 0 && (array[j] > insertValue); j = j - gap) {
                        array[j + gap] = array[j];
                        insertIdx = insertIdx - gap; // 插入位置向前移动一个gap距离
                    }
                    // 发生移动
                    if (insertIdx != i) {
                        array[insertIdx] = insertValue;
                    }
                }
            }
        }
    }

}
