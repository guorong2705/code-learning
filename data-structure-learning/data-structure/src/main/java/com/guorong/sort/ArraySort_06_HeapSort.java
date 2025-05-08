package com.guorong.sort;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * 堆排序
 */
class ArraySort_06_HeapSort extends ArraySort {


    /**
     * 测试经典堆排序
     */
    @Test
    void testStandardHeadSort() {
        Consumer<int[]> heapSort = new StandardHeadSort()::heapSort;
        new ArraySort.ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, heapSort);
    }

    private static class StandardHeadSort {
        public void heapSort(int[] array) {
            // 将全部非叶子节点下沉堆化(n / 2 - 1) 到0都是非叶子节点
            int n = array.length;
            for (int i = (n / 2 - 1); i >= 0; i--) {
                heapify(array, n, i);
            }
            // 将堆顶元素逐个放到数组最后位置,及将最大值放到最后
            for (int heapSize = n - 1; heapSize > 0; heapSize--) {
                ArrayUtils.swap(array, 0, heapSize);
                heapify(array, heapSize, 0);
            }
        }


        /**
         * 堆化
         *
         * @param array    堆化数组
         * @param heapSize 堆大小
         * @param root     堆化的根节点
         */
        private void heapify(int[] array, int heapSize, int root) {
            int maxValueIdx = root;
            int left = root * 2 + 1;
            int right = root * 2 + 2;
            // 替换最大值
            if (left < heapSize && array[left] > array[maxValueIdx]) {
                maxValueIdx = left;
            }
            if (right < heapSize && array[right] > array[maxValueIdx]) {
                maxValueIdx = right;
            }
            if (maxValueIdx != root) {
                ArrayUtils.swap(array, root, maxValueIdx);
                // 继续下沉堆化
                heapify(array, heapSize, maxValueIdx);
            }
        }
    }
}
