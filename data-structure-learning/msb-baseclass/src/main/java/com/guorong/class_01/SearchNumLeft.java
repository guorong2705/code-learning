package com.guorong.class_01;

import com.guorong.ArrayUtils;

import java.util.Arrays;

/**
 * 2-在一个有序数组中，找 >= 某个数最左侧的位置
 */
class SearchNumLeft {

    public static void main(String[] args) {
        int maxLen = 1000;
        int maxValue = 1000;
        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            ArrayUtils.sort(array);
            // 随机值
            int target = ArrayUtils.generateRandInt(maxValue);
            // 查找结果
            int searchIdx = searchGreaterNum(array, target);
            // 参考数据
            int referenceIdx = iterSearch(array, target);
            if (searchIdx != referenceIdx) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("参考结果：" + referenceIdx);
                System.out.println("查找结果：" + searchIdx);
                return;
            }
        }
    }

    // 使用二分查找
    private static int searchGreaterNum(int[] array, int target) {
        int index = -1;
        if (array == null || array.length == 0) {
            return index;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (array[mid] >= target) {
                right = mid - 1;
                index = mid; // 找到一个标记
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    // 暴力查找
    private static int iterSearch(int[] array, int target) {
        int index = -1;
        if (array == null || array.length == 0) {
            return index;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= target) {
                index = i;
                break;
            }
        }
        return index;
    }

}
