package com.guorong.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 数值流 演示
 *
 * Java 8 引入了三个原始类型特化流接口来解决这个问题(装箱和拆箱的数字效率问题).
 * IntStream、DoubleStream和 LongStream , 分别将流中的元素特化为 int、long 和 double , 从而避免了暗含的装箱成本。
 * 每个接口都带来了进行常用数值归约的新方法，比如对数值流求和的 sum , 找到最大元素的 max 。
 * 此外还有在必要时再把它们转换回对象流的方法。要记住的是，这些特化的原因并不在于流的复杂性，
 * 而是装箱造成的复杂性——即类似 int 和 Integer 之间的效率差异。
 *
 * @author guorong
 * @create 2019-12-03
 */
public class NumberStreamTest {

    @Data
    @AllArgsConstructor
    private class Apple {
        private String color;
        private double weight;
    }
    private List<Apple> appleList = Arrays.asList(
            new Apple("red", 150),
            new Apple("blue", 100),
            new Apple("yellow", 180),
            new Apple("green", 170),
            new Apple("orange", 160),
            new Apple("orange", 160)
    );


    /**
     * 1. 映射到数值流 (mapToInt、mapToDouble、mapToLong)
     */
    public void testMapToDouble() {
        /**
         * 映射为double数值流
         */
        DoubleStream doubleStream = appleList.stream().mapToDouble(Apple::getWeight);
        /**
         * 获取元素的和(默认值为0，流中不存在元素时)
         */
        double sum = doubleStream.sum();
        /**
         * 最大值
         */
        OptionalDouble max = doubleStream.max();
        /**
         * 最小值
         */
        OptionalDouble min = doubleStream.min();
        /**
         * 平均值
         */
        OptionalDouble average = doubleStream.average();
    }


    /**
     * 2. 转换回对象流 (boxed)
     */
    @Test
    public void testBoxed(){
        // 获取数值流
        DoubleStream doubleStream = appleList.stream().mapToDouble(Apple::getWeight);
        // 转换成对象流
        Stream<Double> stream = doubleStream.boxed();
    }


    /**
     * 3. 数值范围
     * Java 8引入了两个可以用于 IntStream 和 LongStream 的静态方法，帮助生成这种范围：
     * range 和 rangeClosed 。这两个方法都是第一个参数接受起始值，第二个参数接受结束值。
     * 但 range 是不包含结束值的，而 rangeClosed 则包含结束值
     */
    @Test
    public void testRange(){
        /**
         * range 生成数值 1,2,3,4...99的元素 总共99个元素
         * 并获取其中的偶数
         */
        IntStream range = IntStream.range(1, 100).filter(i -> i % 2 == 0);

        /**
         * rangeClosed 生成数值 1,2,3,4...99,100的元素 总共100个元素
         * 并获取其中的奇数
         */
        IntStream rangeClosed = IntStream.rangeClosed(1, 100).filter(i -> i % 2 != 0);
    }
}
