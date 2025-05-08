package com.guorong.heap;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MaxHeapTests {

    // 测试插入堆
    @Test
    public void testInsert() {
        int maxLen = 100;
        int maxValue = 100;
        int testCount = 10000 * 10;
        for (int testIdx = 0; testIdx < testCount; testIdx++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            // int[] array = {89, 32, 44, 38, 69, 51, 93, 49, 89, 60, 45, 74, 15, 81};
            int size = array.length;
            // 在堆中存入值
            MaxHeap maxHeap = new MaxHeap(size);
            for (int i = 0; i < size; i++) {
                maxHeap.insert(array[i]);
            }
            // 非降序错误
            if (!maxHeap.isHeap()) {
                System.out.println("错误原始插入数组:" + Arrays.toString(array));
                System.out.println("===============================================");
            }
        }
    }

    // 测试堆化
    @Test
    public void testBuildHeap() {
        int maxLen = 20;
        int maxValue = 100;
        int testCount = 10000 * 10;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
            int len = array.length;
            if (len == 0) {
                continue;
            }
            MaxHeap maxHeap = new MaxHeap(len);
            maxHeap.buildHeap(array);
            // 非降序为错误
            if (!maxHeap.isHeap()) {
                System.out.println("原始数组:" + Arrays.toString(array));
            }
        }
    }

    // 测试提取堆顶元素
    @Test
    public void testExtract() {
        int maxLen = 20;
        int maxValue = 100;
        int testCount = 10000 * 10;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateValueRandomArray(maxLen, maxValue);
            MaxHeap maxHeap = new MaxHeap(array.length);
            maxHeap.buildHeap(array);
            // 判断是否符合堆的定义
            if (!maxHeap.isHeap()) {
                System.out.println("错误数据1:" + Arrays.toString(array));
            }
            // 测试获取堆顶方法
            boolean isError = false;
            for (int j = 0; j < array.length; j++) {
                maxHeap.extract();
                if (!maxHeap.isHeap()) {
                    isError = true;
                }
            }
            if (isError) {
                System.out.println("错误数据2:" + Arrays.toString(array));
            }
        }
    }

    // 测试删除指定索引的节点
    @Test
    public void testRemoveAt() {
        int maxLen = 20;
        int maxValue = 100;
        int testCount = 10000 * 1;
        for (int i = 0; i < testCount; i++) {
            int[] array = ArrayUtils.generateValueRandomArray(maxLen, maxValue);
            // 构建堆
            MaxHeap maxHeap = new MaxHeap(array.length);
            maxHeap.buildHeap(array);
            // 判断是否符合堆的定义
            if (!maxHeap.isHeap()) {
                System.out.println("错误数据1:" + Arrays.toString(array));
            }
            for (int j = 0; j < array.length; j++) {
                // 随机移除元素
                int randomIndex = (int) (Math.random() * maxHeap.size());
                maxHeap.removeAt(randomIndex);
                // 判断是否符合堆的定义
                if (!maxHeap.isHeap()) {
                    System.out.println("错误数据2:" + Arrays.toString(array));
                }
            }
        }
    }

}


















