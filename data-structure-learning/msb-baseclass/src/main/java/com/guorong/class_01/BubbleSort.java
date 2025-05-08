package com.guorong.class_01;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 冒泡排序
 */
class BubbleSort {

    @Test
    void testBubbleSort() {
        int maxLen = 1000;
        int maxValue = 100;
        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            int[] oneArray = Arrays.copyOf(array, array.length);
            int[] twoArray = Arrays.copyOf(array, array.length);
            bubbleSort(oneArray);
            Arrays.sort(twoArray);
            if (!Arrays.equals(oneArray, twoArray)) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("排序数据：" + Arrays.toString(oneArray));
                System.out.println("参照数据：" + Arrays.toString(twoArray));
                return;
            }
        }
    }

    void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int n = array.length;
        for (int i = n - 1; i > 0; i--) {
            boolean isSwap = false;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    ArrayUtils.swap(array, j, j + 1);
                    isSwap = true;
                }
            }
            if (!isSwap) {
                break;
            }
        }

    }
}
