package com.guorong.class_01;

import com.guorong.ArrayUtils;

import java.util.Arrays;

/**
 * 3-在一个有序数组中，找 <= 某个数最右侧的位置
 */
class SearchNumRight {
    public static void main(String[] args) {
        int maxLen = 8;
        int maxValue = 1000;
        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            // 生成随机有序数组
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            ArrayUtils.sort(array);
            // 生成随机查找值
            int target = ArrayUtils.generateRandInt(maxValue);
            // 生成参考数据和实际查找数据
            int referenceResult = iterSearch(array, target);
            int searchResult = searchNumRight(array, target);
            // 验证结果
            if (referenceResult != searchResult) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("参考数据：" + referenceResult);
                System.out.println("实际数据：" + searchResult);
                return;
            }
        }
    }

    // 二分查找
    static int searchNumRight(int[] array, int target) {
        int index = -1;
        if (array == null || array.length == 0) {
            return index;
        }
        // 二分查找
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (array[mid] <= target) {
                index = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }

    static int iterSearch(int[] array, int target) {
        int index = -1;
        if (array == null || array.length == 0) {
            return index;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] <= target) {
                index = i;
                break;
            }
        }
        return index;
    }
}
