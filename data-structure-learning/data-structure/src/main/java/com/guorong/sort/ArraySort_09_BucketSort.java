package com.guorong.sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 桶排序（Bucket Sort）
 */
class ArraySort_09_BucketSort extends ArraySort{

    /**
     * 测试桶排序
     */
    @Test
    void testStandardBucketSort() {
        StandardBucketSort standardBucketSort = new StandardBucketSort();
        new ArraySortLogarithmic().runArraySort(TEST_CASE_COUNT, standardBucketSort::bucketSort);
    }


    // 标准桶排序
    private static class StandardBucketSort {

        public void bucketSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            int n = array.length;
            // 动态选择桶数量，例如 sqrt(n) 或 n/10
            int bucketCount = (int) Math.sqrt(n);
            bucketCount = Math.max(1, bucketCount);
            // 创建桶
            @SuppressWarnings("unchecked")
            ArrayList<Integer>[] buckets = new ArrayList[bucketCount];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new ArrayList<>();
            }
            // 获取元素的最大值和最小值
            int min = array[0];
            int max = array[0];
            for (int i = 1; i < n; i++) {
                min = Math.min(min, array[i]);
                max = Math.max(max, array[i]);
            }
            // 获取桶的值的区间长度(用于归一化操作)
            int range = max - min;
            if (range == 0) {
                return; // 值都是相同的
            }
            // 将元素分配到桶中
            for (int value : array) {
                // 归一化到 [0, bucketCount-1] 区间
                int bucketIdx = ((value - min) / range) * (bucketCount - 1);
                buckets[bucketIdx].add(value);
            }
            // 对每个桶中的元素排序
            for (ArrayList<Integer> bucket : buckets) {
                Collections.sort(bucket);
            }
            // 按顺序将桶中的元素返回原数组
            int idx = 0;
            for (ArrayList<Integer> bucket : buckets) {
                for (Integer value : bucket) {
                    array[idx++] = value;
                }
            }
        }
    }
}
