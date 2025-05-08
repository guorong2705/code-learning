package com.guorong.class_01;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 插入排序
 */
class InsertSort {

    @Test
    void testInsertSort() {
        int maxLen = 10000;
        int maxValue = 1000;
        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            int[] oneArray = Arrays.copyOf(array, array.length);
            int[] twoArray = Arrays.copyOf(array, array.length);
            insertSort(oneArray);
            Arrays.sort(twoArray);
            if (!Arrays.equals(oneArray, twoArray)) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("排序数据：" + Arrays.toString(oneArray));
                System.out.println("参照数据：" + Arrays.toString(twoArray));
                return;
            }
        }
    }

    void insertSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int value = array[i];
            int idx = i;
            for (int j = i - 1; j >= 0; j--) {
                if (value < array[j]) {
                    array[j + 1] = array[j];
                    idx--;
                }
            }
            if (idx != i) {
                array[idx] = value;
            }
        }
    }
}
