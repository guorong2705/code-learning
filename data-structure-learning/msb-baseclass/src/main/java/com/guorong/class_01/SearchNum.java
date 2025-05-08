package com.guorong.class_01;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 1-在一个有序数组中，找某个数是否存在
 */
class SearchNum {

    @Test
    void testSearchNum() {
        int maxLen = 1000;
        int maxValue = 1000;
        int testCount = 10000 * 10;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            ArrayUtils.sort(array);
            int target = ArrayUtils.generateRandInt(maxValue);
            boolean searchResult = searchNumByBinarySearch(array, target);
            boolean referenceResult = ArrayUtils.searchNum(array, target);
            if (searchResult != referenceResult) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("参看结果：" + referenceResult);
                System.out.println("查找结果：" + searchResult);
                return;
            }
        }
    }


    // 使用二分查找
    boolean searchNumByBinarySearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            return false;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 2);
            if (target == array[mid]) {
                return true;
            } else if (target < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
