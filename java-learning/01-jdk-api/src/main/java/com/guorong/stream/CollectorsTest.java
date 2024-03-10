package com.guorong.stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author guorong
 * @date 2021-08-14
 */
public class CollectorsTest {


    private List<Apple> appleList = Arrays.asList(
            new Apple("red", 150),
            new Apple("blue", 100),
            new Apple("blue", 120),
            new Apple("yellow", 180),
            new Apple("green", 170),
            new Apple("orange", 160),
            new Apple("orange", 160)
    );

    @Test
    public void test() {
        Map<Apple, List<Apple>> collect = appleList.stream().collect(Collectors.groupingBy(apple -> new Apple()));
        System.out.println("------------");
    }


    /**
     * 收集颜色字段值到 TreeSet 中
     */
    @Test
    public void testToCollection() {
        TreeSet<String> colorTreeSet = appleList.stream()
                .map(Apple::getColor)
                .collect(Collectors.toCollection(TreeSet::new));

        colorTreeSet.forEach(System.out::println);
    }

    /**
     * 收集颜色字段值到 List 中
     */
    @Test
    public void testToList() {
        List<String> collect = appleList.stream()
                .map(Apple::getColor)
                .collect(Collectors.toList());
    }

    @Test
    public void testToMap() {
        List<Apple> list = Arrays.asList(
                new Apple("red", 150),
                new Apple("blue", 100),
                new Apple("yellow", 180),
                new Apple("green", 170),
                new Apple("orange", 160)
        );

        // 转换成map，如果key重复会报错
        Map<String, Double> map = list.stream().collect(Collectors.toMap(apple -> apple.getColor(), apple -> apple.getWeight()));
        System.out.println(map);
    }



    /**
     * 将元素转换为字符串并将它们连接起来，用逗号分隔
     */
    @Test
    public void testJoining() {
        String colorStr = appleList.stream()
                .map(Apple::getColor)
                .collect(Collectors.joining(","));

        System.out.println(colorStr);
    }


    /**
     * 求和
     */
    @Test
    public void testSumming() {
        // 统计流中元素重量字段值的总和
        Double weightSum = appleList.stream().collect(Collectors.summingDouble(Apple::getWeight));

    }

    /**
     * 根据指定字段进行分组
     */
    @Test
    public void testGroupingBy() {
        Map<String, List<Apple>> map = appleList.stream()
                .collect(Collectors.groupingBy(Apple::getColor));

        map.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .forEach(System.out::println);
    }


    /**
     * 根据指定字段值进行分组，然后在极算每个分组中字段字段值的和
     */
    @Test
    public void testGroupingBySummingDouble() {
        Map<String, Double> map = appleList.stream()
                .collect(
                        Collectors.groupingBy(
                                Apple::getColor, Collectors.summingDouble(Apple::getWeight)
                        )
                );
        map.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .forEach(System.out::println);
    }

    /**
     * 将苹果分为 重量大于150的 和 重量小于等于150的
     */
    @Test
    public void testPartitioningBy() {
        Map<Boolean, List<Apple>> map = appleList.stream()
                .collect(Collectors.partitioningBy(apple -> apple.getWeight() - 150 > 0));

        map.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .forEach(System.out::println);
    }


}
