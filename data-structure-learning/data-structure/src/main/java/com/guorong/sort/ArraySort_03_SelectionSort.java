package com.guorong.sort;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * 选择排序：选出最小值放入索引
 */
class ArraySort_03_SelectionSort extends ArraySort {


    /**
     * 测试经典冒泡排序
     */
    @Test
     void testStandardSelectionSort() {
        Consumer<int[]> selectionSort = new StandardSelectionSort()::selectionSort;
        new ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, selectionSort);
    }


    /**
     * 经典选择排序
     */
    private static class StandardSelectionSort {
        public void selectionSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            int n = array.length;
            for (int i = 0; i < n - 1; i++) { // 将当前最小值放入i索引处
                int minIdx = i;
                for (int j = i + 1; j < n; j++) {
                    if (array[minIdx] > array[j]) {
                        minIdx = j; // 更新最小值索引
                    }
                }
                // 判断是否更新过minIdx
                if (minIdx != i) {
                    ArrayUtils.swap(array, minIdx, i);
                }
            }
        }
    }

}
