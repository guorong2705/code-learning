package com.guorong.easy_leetcode;

/**
 * 4. 寻找两个正序数组的中位数
 */
public class Solution_0004 {
    public static void main(String[] args) {
        int[] num1 = {1, 3};
        int[] num2 = {2};
        findMedianSortedArrays1(num1, num2);
    }

    // 暴力破解
    private static double findMedianSortedArrays0(int[] nums1, int[] nums2) {
        // 将 nums1 和 nums2 合并为一个有序数组
        int[] array = new int[nums1.length + nums2.length];
        int idx = 0;
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < nums1.length && idx2 < nums2.length) {
            if (nums1[idx1] < nums2[idx2]) {
                array[idx++] = nums1[idx1++];
            } else {
                array[idx++] = nums2[idx2++];
            }
        }
        while (idx1 < nums1.length) {
            array[idx++] = nums1[idx1++];
        }
        while (idx2 < nums2.length) {
            array[idx++] = nums2[idx2++];
        }
        // 计算合并后的中位数
        double median;
        int index = (array.length >> 1) - 1; // 减去1,因为索引从0开始
        if (array.length % 2 == 0) {
            median = (array[index] + array[index + 1]) * 0.5;
        } else {
            median = array[index + 1];
        }
        return median;
    }

    private static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // 中位数值
        int pre = -1;
        int current = -1;
        int idx1 = 0;
        int idx2 = 0;
        int len = nums1.length + nums2.length;
        // 将pre和current设置为中位数
        for (int i = 0; i <= len / 2; i++) {
            pre = current;
            if (idx1 < nums1.length && (idx2 >= nums2.length || nums1[idx1] < nums2[idx2])) {
                current = nums1[idx1++];
            } else {
                current = nums2[idx2++];
            }
        }
        // 长度奇偶性
        if (len % 2 == 0) {
            return (pre + current) / 2.0;
        } else {
            return current;
        }
    }

}