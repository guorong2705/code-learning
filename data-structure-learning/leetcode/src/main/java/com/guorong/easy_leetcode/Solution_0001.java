package com.guorong.easy_leetcode;

import java.util.HashMap;
import java.util.Random;

/**
 * 1. 两数之和
 * 给定一个整数数组nums和一个整数目标值 target，请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 * 你可以按任意顺序返回答案。
 */
public class Solution_0001 {
    public static void main(String[] args) {

    }

    // 创建随机数组
    // ==================================================

    // 暴力破解
    public int[] twoSum0(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSum1(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            int other = target - nums[i];
            map.put(other, i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer idx = map.get(nums[i]);
            if (idx != null && idx != i) {
                return new int[]{i, idx};
            }
        }
        return new int[0];
    }

    public int[] twoSum2(int[] nums, int target) {
        // [2,7,11,15]
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            int other = target - nums[i];
            if (map.get(other) != null) {
                return new int[]{map.get(other), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

}