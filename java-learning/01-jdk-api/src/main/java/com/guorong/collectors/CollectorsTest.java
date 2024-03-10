package com.guorong.collectors;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Collectors 演示
 * @author guorong
 * @create 2019-12-03
 */
public class CollectorsTest {

    private List<Apple> appleList = Arrays.asList(
            new Apple("red",150),
            new Apple("blue", 100),
            new Apple("red",180),
            new Apple("green",170),
            new Apple("orange",160),
            new Apple("orange",120)
    );


    /**
     * 汇总和归约
     */
    @Test
    public void test01(){

        /**
         * (1)----求和----
         * Collectors.summingLong 和 Collectors.summingDouble 方法的作用完全一样，可以用于求和字段为 long 或 double 的情况。
         */
        Double totalWeight01 = appleList.stream()
                .collect(Collectors.summingDouble(Apple::getWeight));

        // 转换为数值流再求和
        double totalWeight02 = appleList.stream()
                .mapToDouble(Apple::getWeight).sum();



        /**
         * (2)----计算平均数----
         * 汇总不仅仅是求和；还有 Collectors.averagingInt ，
         * 连同对应的 averagingLong 和 averagingDouble 可以计算数值的平均数
         */
        Double average01 = appleList.stream()
                .collect(Collectors.averagingDouble(Apple::getWeight));

        // 转换为数值流再求和
        OptionalDouble average02 = appleList.stream()
                .mapToDouble(Apple::getWeight).average();


        /**
         * (3)----获取元素的个数----
         */
        long count1 = appleList.stream().count();
        long count2 = appleList.stream().collect(Collectors.counting());


        /**
         * (4)----查找流中的最大值----
         */
        // 重量最大的苹果
        Optional<Apple> max = appleList.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Apple::getWeight)));


        /**
         * (5)----查找流中的最小值----
         */
        // 重量最小的苹果
        Optional<Apple> min = appleList.stream()
                .collect(Collectors.minBy(Comparator.comparingDouble(Apple::getWeight)));


        /**
         * (6)----连接字符串----
         * 连接苹果颜色，使用 "," 符号分割
         */
        String colorStr = appleList.stream().map(Apple::getColor).collect(Collectors.joining(","));
    }




    /**
     * 提取对象属性作为 Map 的 key
     */
    @Test
    public void testCollectors_toMap(){
        List<Apple> apples = Arrays.asList(
                new Apple("red",150),
                new Apple("blue", 100),
                new Apple("red",150),
                new Apple("green",170)
        );

        // 去除重复元素
        Stream<Apple> distinct = apples.stream().distinct();

        // 将Apple的Color属性作为key
        Map<String, Apple> map = distinct.collect(Collectors.toMap(Apple::getColor, apple -> apple));
        // 遍历
        map.forEach((key,apple) -> System.out.println(key +" = " + apple));

    }



    /**
     * 所有归约和汇总收集器,都是Collectors.reducing工厂方法的特殊情况。
     */
    @Test
    public void testCollectors_reducing(){
        /**
         * (1) 三个参数
         * 第1个参数: 初始值
         * 第2个参数: 转换函数
         * 第3个参数：累积函数
         */
        // 获取苹果的总重量
        Double totalWeight = appleList.stream()
                .collect(Collectors.reducing(0.0, Apple::getWeight, Double::sum));

        // 获取元素个数
        Long count = appleList.stream()
                .collect(Collectors.reducing(0L, apple -> 1L, Long::sum));


        /**
         * (2) 单个参数：BinaryOperator<T> op
         */
        // 获取最大重量的苹果
        Optional<Apple> maxWeightApple = appleList.stream()
                .collect(Collectors.reducing(((a1, a2) -> a1.getWeight() > a2.getWeight() ? a1 : a2)));
    }


    /**
     * 分组
     */
    @Test
    public void testGroupingBy(){
        /**
         * （1）一级分组
         */
        System.out.println("=================根据苹果颜色进行分组======================");
        Map<String, List<Apple>> mapByColor = appleList.stream()
                .collect(Collectors.groupingBy(Apple::getColor));

        mapByColor.forEach((color, apples) -> {
            System.out.println(color +"=" +apples);
        });

        System.out.println("=================根据重量进行分组: 大于150是Heavy,小于等于150的为Light======================");
        Map<String, List<Apple>> mapByWeight = appleList.stream()
                .collect(Collectors.groupingBy(apple -> apple.getWeight() > 150 ? "Heavy" : "Light"));

        mapByWeight.forEach((s, apples) -> {
            System.out.println(s + "=" + apples);
        });


        /**
         * （2）多级分组
         * 要实现多级分组，可以使用一个双参数版本的 Collectors.groupingBy 工厂方法创建的收集器，
         * 它除了普通的分类函数之外，还可以接受 collector 类型的第二个参数。要进行二级分组的话，
         * 可以把一个内层 groupingBy 传递给外层 groupingBy,并定义一个为流中项目分类的二级标准
         */
        //
        System.out.println("=================先根据苹果颜色分类，然后再根据重量分类：大于150是Heavy,小于等于150的为Light======================");
        Map<String, Map<String, List<Apple>>> mapColorByWeight = appleList.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.groupingBy(apple -> apple.getWeight() > 150? "Heavy" : "Light")));
        mapColorByWeight.forEach((s, map) -> System.out.println(s + "=" +map));


        /**
         * (3) 按子组收集数据
         */
        System.out.println("================统计每种颜色苹果的数量======================");
        Map<String, Long> mapColorAndCount = appleList.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.counting()));
        mapColorAndCount.forEach((key,value) -> System.out.println(key + "=" + value));

        System.out.println("================获取每种颜色中最大重量的苹果======================");
        Map<String, Optional<Apple>> map = appleList.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.maxBy(Comparator.comparingDouble(Apple::getWeight))));
        map.forEach((s, optional) -> System.out.println(s + "=" + optional.get()));
    }


    /**
     * 分区
     * 分区是分组的特殊情况：由一个谓词（返回一个布尔值的函数）作为分类函数，它称分区函数。
     * 分区函数返回一个布尔值，这意味着得到的分组 Map 的键类型是 Boolean ，于是它最多可以
     * 分为两组: true 是一组, false 是一组.
     */
    @Test
    public void test(){
        /**
         * 苹果分区为两组：大于150的一组，其他一组
         */
        Map<Boolean, List<Apple>> map = appleList.stream()
                .collect(Collectors.partitioningBy(apple -> apple.getWeight() > 150));
        System.out.println(map.toString());

    }





}
