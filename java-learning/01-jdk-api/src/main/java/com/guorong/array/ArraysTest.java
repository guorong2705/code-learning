package com.guorong.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Arrays类的演示
 */
public class ArraysTest {

    private static int[] intArray = {11, 22, 33, 44, 55, 66, 77, 88, 99};

    @Test
    public void testCopyOf() {
        int[] resultArray = Arrays.copyOf(intArray, 5);
        // [11, 22, 33, 44, 55]
        System.out.println(Arrays.toString(resultArray));
    }


    @Test
    public void testCopyOfRange() {
        int[] resultArray = Arrays.copyOfRange(intArray, 0, 4);
        // [11, 22, 33, 44]
        System.out.println(Arrays.toString(resultArray));
    }


    /**
     * 采用优化的快速排序算法对数组进行排序。
     */
    @Test
    public void testSort() {
        Arrays.sort(intArray);
        System.out.println(Arrays.toString(intArray));
    }

    /**
     * 采用二分搜索算法查找值 v。如果查找成功，则返回相应的下标值；否则，返回一个负数值
     */
    @Test
    public void testBinary() {
        // 3
        System.out.println(Arrays.binarySearch(intArray, 44));
        // -1
        System.out.println(Arrays.binarySearch(intArray, 30));
    }

    /**
     * 将数组的所有数据元素值设置为 V。
     */
    @Test
    public void testFill() {
        Arrays.fill(intArray, 100);
        System.out.println(Arrays.toString(intArray));
    }


    /**
     * 判断两个数组是否相等
     */
    @Test
    public void testEquals() {
        int[] arraySource = {11};
        int[] arrayTarget = {};
        /**
         * 如果两个数组大小相同， 并且下标相同的元素都对应相等， 返回 true。
         * 参数：a、b 类型为 int、long、short、char、byte、boolean、float 或 double 的两个数组。
         */
        System.out.println(Arrays.equals(arraySource, arrayTarget));
    }





}
