package com.guorong.class_01;

import com.guorong.ArrayUtils;

import java.util.Arrays;

/**
 * 局部最小值问题(相邻两个数不重复)，注意边界条件索引为 0 和 n-1
 */
class LocalMinimum {

    public static void main(String[] args) {
        int maxLen = 1000;
        int maxValue = 100;
        int testCount = 10000 * 100000;
        for (int i = 0; i < testCount; i++) {
            // 生成随机数组，相邻两个数不相等
            int[] array = generateArray(maxLen, maxValue);
            int len = array.length;
            // 执行执行局部最小方法
            int minIdx = findLocalMinimum(array);
            if (minIdx == -1) {
                return;
            }
            // 左侧值
            int leftValue = minIdx == 0 ? Integer.MAX_VALUE : array[minIdx - 1];
            // 右侧值
            int rightValue = minIdx == len - 1 ? Integer.MAX_VALUE : array[minIdx + 1];
            // 判断是否不符合小于左右两边值
            if (array[minIdx] >= leftValue || array[minIdx] >= rightValue) {
                System.out.println("输入数据：" + Arrays.toString(array));
                System.out.println("局部最小索引：" + minIdx);
                System.out.println("左侧值：" + leftValue + " 右侧值：" + rightValue);
                return;
            }
        }
    }

    /**
     * 查找局部最小值
     *
     * @param array
     * @return
     */
    private static int findLocalMinimum(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int maxIdx = array.length - 1;
        int left = 0;
        int right = maxIdx;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int midValue = array[mid];
            // mid左边索引的值
            int midLeftValue = mid == 0 ? Integer.MAX_VALUE : array[mid - 1];
            // mid右边索引的值
            int midRightValue = mid == maxIdx ? Integer.MAX_VALUE : array[mid + 1];

            // 局部最小：左边和右边都比mid的值大
            if (midValue < midLeftValue && midValue < midRightValue) {
                return mid;
            }
            // 左侧存在更小值
            else if (midValue > midLeftValue) {
                right = mid - 1;
            }
            // 右侧存在更小值
            else if (midValue > midRightValue) {
                left = mid + 1;
            }
        }
        return -1;
    }

    // 生成随机数组，相邻元素不相等
    private static int[] generateArray(int maxLen, int maxValue) {
        int len = ArrayUtils.generateRandInt(maxLen);
        int[] array = new int[len];
        for (int idx = 0; idx < len; idx++) {
            while (true) {
                int value = ArrayUtils.generateRandInt(maxValue);
                if (idx == 0 || value != array[idx - 1]) {
                    array[idx] = value;
                    break;
                }
            }
        }
        return array;
    }


}
