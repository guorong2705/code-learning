package com.guorong.class_01;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 选择排序
 */
class SelectionSort {

    @Test
    void testSelectionSort() {
        int maxLen = 1000;
        int maxValue = 1000;
        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            int[] oneArray = Arrays.copyOf(array, array.length);
            int[] twoArray = Arrays.copyOf(array, array.length);
            selectionSort(oneArray);
            Arrays.sort(twoArray);
            if (!Arrays.equals(oneArray, twoArray)) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("排序数据：" + Arrays.toString(oneArray));
                System.out.println("参照数据：" + Arrays.toString(twoArray));
                return;
            }
        }
    }


    void selectionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[minIdx] > array[j]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                ArrayUtils.swap(array, i, minIdx);
            }
        }
    }
}
