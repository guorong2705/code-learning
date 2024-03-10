package com.guorong.generic.demo;

import java.util.Arrays;
import java.util.Random;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

/**
 * 填充数组元素
 */
public class FillArray {

    // 引用类型数组
    private static <T> T[] fill(T[] array, Supplier<T> supplier) {
        Arrays.setAll(array, n -> supplier.get());
        return array;
    }

    // int 基本类型数组
    private static int[] fillInt(int[] array, IntSupplier intSupplier) {
        Arrays.setAll(array, n -> intSupplier.getAsInt());
        return array;
    }

    // long 基本类型数组
    private static long[] fillLong(long[] array, LongSupplier longSupplier) {
        Arrays.setAll(array, n -> longSupplier.getAsLong());
        return array;
    }

    // double 基本类型数组
    private static double[] fillDouble(double[] array, DoubleSupplier doubleSupplier) {
        Arrays.setAll(array, n -> doubleSupplier.getAsDouble());
        return array;
    }


    public static void main(String[] args) {
        Random random = new Random(47);
        // 填充引用类型数组
        String[] strArray = fill(new String[5], () -> String.valueOf(random.nextFloat()));
        System.out.println(Arrays.toString(strArray));
        // 填充基本类型数组
        int[] intArrays = fillInt(new int[5], random::nextInt);
        System.out.println(Arrays.toString(intArrays));
    }

}
