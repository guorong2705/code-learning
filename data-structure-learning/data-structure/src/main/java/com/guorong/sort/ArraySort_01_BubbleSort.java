package com.guorong.sort;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * 冒泡排序
 */
class ArraySort_01_BubbleSort extends ArraySort {

    /**
     * 测试经典冒泡排序
     */
    @Test
     void testStandardBubbleSort() {
        Consumer<int[]> bubbleSort = new StandardBubbleSort()::bubbleSort;
        new ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, bubbleSort);
    }


    /**
     * 经典冒泡排序
     */
    private static class StandardBubbleSort {
        public void bubbleSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            int n = array.length;
            for (int i = 0; i < n - 1; i++) { // 冒泡比较(n-1)轮
                boolean isSwap = false; // 是否发生交换标记
                for (int idx = 0; idx < n - 1 - i; idx++) { // 内循环比较相邻元素
                    if (array[idx] > array[idx + 1]) {
                        ArrayUtils.swap(array, idx, idx + 1);
                        isSwap = true;
                    }
                }
                if (!isSwap) { // 为发生过交换，说明数组已经有序，不需要就行下一轮
                    break;
                }
            }
        }
    }

}
