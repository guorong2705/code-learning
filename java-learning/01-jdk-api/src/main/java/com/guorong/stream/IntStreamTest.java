package com.guorong.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * IntStream 演示
 */
public class IntStreamTest {


    /**
     * 基础类型流 装箱为 包装类型流
     * 将 intStream -> Stream<Integer>
     */
    @Test
    public void testBoxed() {
        int[] array = {1,2,3,4,5,6,7};
        IntStream stream = Arrays.stream(array);
        Stream<Integer> boxed = stream.boxed();
    }


    /**
     * 生成指定范围内的随机数: 包含5个元素是,范围为: 5-20 之间
     */
    @Test
    public void testCreate01() {
        Random random = new Random(47);
        IntStream intStream = random.ints(5, 20).limit(5);
        // 打印元素
        intStream.forEach(System.out::println);
    }

    /**
     * 生产序列: 元素包含: 1 2 3 4 5 6 .... 100
     */
    @Test
    public void testCreate02() {
        IntStream range = IntStream.range(1, 101);
        range.boxed().map(i -> i + " ").forEach(System.out::print);

    }


    /**
     *
     * IntStream generate(IntSupplier s)
     * 根据传入的生成函数,生成流中的元素: 例如: 生成10个范围在[0,10)之间的数
     */
    @Test
    public void testGenerate(){
        Random random = new Random();

        IntStream generate = IntStream.generate(() -> random.nextInt(10)).limit(10);

        generate.forEach(System.out::println);
    }




    /**
     * 统计：最大值，最小值，平均值, 求和
     */
    @Test
    public void test() {
        // 创建随机数对象
        Random random = new Random();
        /**
         * 1. 获取最大值
         */
        IntStream intStreamMax = random.ints(5, 20).limit(5);
        OptionalInt max = intStreamMax.max();

        /**
         * 2. 最小值
         */
        IntStream intStreamMin = random.ints(5, 20).limit(5);
        OptionalInt min = intStreamMin.min();


        /**
         * 3.平均值
         */
        IntStream intStreamAverage = random.ints(5, 20).limit(6);
        OptionalDouble average = intStreamAverage.average();

        /**
         * 4. 求和
         */
        IntStream intStreamSum = random.ints(5, 30).limit(7);
        int sum = intStreamSum.sum();

    }






}
