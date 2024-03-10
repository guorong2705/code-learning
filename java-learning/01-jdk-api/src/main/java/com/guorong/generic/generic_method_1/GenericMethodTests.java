package com.guorong.generic.generic_method_1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 泛型方法演示
 */
public class GenericMethodTests {

    /**
     * 将 T 类型的 数组 转换为 T 类型的 List
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> fromArrayToList(T[] array) {
        return Arrays.stream(array).collect(Collectors.toList());
    }

    @Test
    public void testFromArrayToList() {
        Integer[] array = {1, 2, 3, 4, 5};
        List<Integer> integerList = GenericMethodTests.fromArrayToList(array);
        integerList.stream().map(i -> i + " ").forEach(System.out::print);
    }


    //======================================================================================

    /**
     * 定义类型的上限( T 必须是 Number 或者 Number 子类 )
     * 将 T 类型的 数组 转换为 T 类型的 List
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T extends Number> List<T> ArrayToList(T[] array) {
        return Arrays.stream(array).collect(Collectors.toList());
    }

    @Test
    public void testArrayToList() {
        // 1. 转换 Integer 类型
        Integer[] integerArray = {1, 2, 3, 4, 5};
        List<Integer> integerList = GenericMethodTests.ArrayToList(integerArray);

        // 2. 转换 Double 类型
        Double[] doubleArray = {12.1, 13.2, 25.2};
        List<Double> doubleList = GenericMethodTests.ArrayToList(doubleArray);
    }


    //============================================================================================

    /**
     * 使用泛型通配符 "?"，定义集合元素的上限，现在可以打印集合元素为 NUmber或者 Number的子类
     *
     * @param list
     */
    public static void printListElementUpgrade(List<? extends Number> list) {
        list.stream().map(number -> number + " ").forEach(System.out::print);
    }

    @Test
    public void testPrintListElementUpgrade() {
        // 1. 打印 Number 的子类 Double
        List<Double> doubleList = new ArrayList<>(Arrays.asList(12.1, 30.2, 25.1, 40.5));
        GenericMethodTests.printListElementUpgrade(doubleList);

        System.out.println();

        // 2. 打印 Number 的子类 Integer
        List<Integer> integerList = new ArrayList<>(Arrays.asList(12, 13, 14, 15, 16, 17));
        GenericMethodTests.printListElementUpgrade(integerList);

    }


}
