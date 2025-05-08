package com.guorong.class_03;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    // 测试归并排序(递归版本)
    @Test
    public void testMerSortRecursion() {
        int maxLen = 20;
        int maxValue = 100;
        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            int[] array = generateRandomArray(maxLen, maxValue);
            mergeSortRecursion(array);
            if (!isSorted(array)) {
                System.out.println("数组无序=" + Arrays.toString(array));
            }
        }
    }

    // 测试归并排序(迭代版本)
    @Test
    public void testMergeSortIteration() {
        int maxLen = 0;
        int maxValue = 100;
        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            int[] array = generateRandomArray(maxLen, maxValue);
            mergeSortIteration(array);
            if (!isSorted(array)) {
                System.out.println("数组无序=" + Arrays.toString(array));
            }
        }
    }

    

    // 归并排序(递归版本)
    public void mergeSortRecursion(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        divide(array, 0, array.length - 1);
    }

    // 切分到最后一个数，就是有序的
    private void divide(int[] array, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        divide(array, left, mid);
        divide(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    // 合并让数组有序
    private void merge(int[] array, int left, int mid, int right) {
        int[] tempArray = new int[right - left + 1];
        int tIdx = 0;
        int lIdx = left;
        int rIdx = mid + 1;
        // 将小的数组先放入临时数组tempArray
        while (lIdx <= mid && rIdx <= right) {
            tempArray[tIdx++] = array[lIdx] < array[rIdx] ? array[lIdx++] : array[rIdx++];
        }
        // 剩余部分处理
        while (lIdx <= mid) {
            tempArray[tIdx++] = array[lIdx++];
        }
        while (rIdx <= right) {
            tempArray[tIdx++] = array[rIdx++];
        }
        // 将临时的有序数组复制会原来的数组
        for (int i = 0; i < tempArray.length; i++) {
            array[left++] = tempArray[i];
        }
    }

    // =================================================================
    public void mergeSortIteration(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int n = array.length;
        int mergeSize = 1; // 最小数组步长
        while (mergeSize < n) {
            int leftIdx = 0;
            while (leftIdx < n) {
                int midIdx = leftIdx + mergeSize - 1;
                // 判断分组中间索引是否超过长度
                if (midIdx >= n) {
                    break;
                }
                // 右侧索引
                int rightIdx = Math.min(midIdx + mergeSize, n - 1);
                merge(array, leftIdx, midIdx, rightIdx);
            }
            // 分组步长翻倍
            if (mergeSize > n / 2) {
                break;
            }
            mergeSize = mergeSize << 1;
        }
    }


    // =================================================================

    // 生成随机数组
    private int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] array = new int[len];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * (maxValue + 1));
        }
        return array;
    }

    // 判断数组是否有序
    private boolean isSorted(int[] array) {
        if (array == null || array.length <= 1) {
            return true;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
