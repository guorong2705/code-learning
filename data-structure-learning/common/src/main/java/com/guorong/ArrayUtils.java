package com.guorong;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

// 数组工具
public final class ArrayUtils {

    private static final Random random = new Random();

    private ArrayUtils() {
    }

    /**
     * 判断数组是否为回文数组
     *
     * @param array int类型数组
     * @return 是回文数组返回true，否则返回false
     */
    public static boolean isPalindromeNum(int[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        int startIdx = 0;
        int endIdx = array.length - 1;
        while (startIdx <= endIdx) {
            if (array[startIdx++] != array[endIdx--]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 交换元素
     *
     * @param array 元素所在的数组
     * @param aIdx  索引
     * @param bIdx  索引
     */
    public static void swap(int[] array, int aIdx, int bIdx) {
        if (array == null || array.length < 2) {
            return;
        }
        boolean flag = aIdx < 0 || aIdx >= array.length || bIdx < 0 || bIdx >= array.length;
        if (flag) {
            throw new IndexOutOfBoundsException("aIdx or bIdx index out");
        }
        if (aIdx == bIdx) { // 同一个位置不需要交换
            return;
        }
        // 交换
        int temp = array[aIdx];
        array[aIdx] = array[bIdx];
        array[bIdx] = temp;
    }

    /**
     * 生成随机数组,长度随机，值也随机
     *
     * @param maxLen   最大数组长度
     * @param maxValue 数组元素最大值
     * @return 随机长度，随机值的数组
     */
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = generateRandInt(maxLen);
        int[] array = new int[len];
        for (int i = 0; i < array.length; i++) {
            array[i] = generateRandInt(maxValue);
        }
        return array;
    }

    /**
     * 生成固定长度的随机值
     *
     * @param len      数组长度
     * @param maxValue 数组元素的最大值
     * @return 返回随机值的数组
     */
    public static int[] generateValueRandomArray(int len, int maxValue) {
        int[] array = new int[len];
        for (int i = 0; i < array.length; i++) {
            array[i] = generateRandInt(maxValue);
        }
        return array;
    }

    /**
     * 生成指定长度的唯一数组
     *
     * @param len      数组长度
     * @param maxValue 数组元素最大值
     * @return 生成的唯一数组
     */
    public static int[] generateUniqueValueArray(int len, int maxValue) {
        if (len >= maxValue) {
            throw new IllegalArgumentException("len must less maxValue");
        }
        // 生成指定长度len长度的随机值
        Set<Integer> set = new HashSet<>();
        while (set.size() < len) {
            set.add(generateRandInt(maxValue));
        }
        // 转换为返回数组
        int[] array = new int[len];
        int idx = 0;
        for (Integer value : set) {
            array[idx++] = value;
        }
        return array;
    }

    /**
     * 对数组进行排序
     *
     * @param array
     */
    public static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        // 使用选择排序
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[minIdx] > array[j]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                swap(array, minIdx, i);
            }
        }
    }


    /**
     * 暴力查找数组
     *
     * @param array 查找数组
     * @param num   目标值
     * @return 找到返回true，否则返回false
     */
    public static boolean searchNum(int[] array, int num) {
        if (array == null) {
            throw new IllegalArgumentException("array is not null");
        }
        for (int value : array) {
            if (value == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * 数组是否为升序
     *
     * @param array 要判定的数组
     * @return 升序返回true，否则返回false
     */
    public static boolean isAscOrder(int[] array) {
        if (array != null) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 数组是否为降序
     *
     * @param array 数组
     * @return 数组是降序返回true，否则返回false
     */
    public static boolean isDescOrder(int[] array) {
        if (array != null) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] < array[i + 1]) { // 判断是否为降序
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断两个数组是否相等
     *
     * @param aArray 数组a
     * @param bArray 数组b
     * @return 两个数组相等返回true，否则返回 false
     */
    public static boolean isEquals(int[] aArray, int[] bArray) {
        if (aArray == bArray) {
            return true; // 是同一个数组，或者两个都以为null
        }
        if (aArray == null || bArray == null || aArray.length != bArray.length) {
            return false;
        }
        for (int i = 0; i < aArray.length; i++) {
            if (aArray[i] != bArray[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 生成[0,max]范围的int值
     *
     * @param max 最大值
     * @return 返回[0, max]范围类的 int数
     */
    public static int generateRandInt(int max) {
        return random.nextInt(max + 1);
    }

}
