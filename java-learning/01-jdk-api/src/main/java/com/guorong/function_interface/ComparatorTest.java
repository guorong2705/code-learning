package com.guorong.function_interface;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 比较器 演示
 *
 * @author guorong
 * @create 2019-11-29
 */
public class ComparatorTest {


    private List<Apple> appleList = Arrays.asList(
            new Apple("red", 12, "China"),
            new Apple("yellow", 15, "USA"),
            new Apple("blue", 20, "USA"),
            new Apple("green", 8, "USA"),
            new Apple("yellow", 15, "China")
    );


    /**
     * 获取比较器
     */
    @Test
    public void test01(){
        System.out.println("==== 苹果重量升序排序 ====");
        appleList.sort(Comparator.comparing(Apple::getWeight));
        appleList.forEach(System.out::println);
    }


    /**
     * 获取逆序比较器: Comparator.comparing(Apple::getWeight).reversed()
     */
    @Test
    public void test02(){
        System.out.println("==== 苹果重量降序排序 ====");
        appleList.sort(Comparator.comparing(Apple::getWeight).reversed());
        appleList.forEach(System.out::println);
    }


    /**
     * 比较器链
     */
    @Test
    public void test03(){
        System.out.println("==== 苹果重量升序,重量相同按照产地升序 ====");
        appleList.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getCountry));
        appleList.forEach(System.out::println);
    }

    /**
     * 提取颜色字段属性值比较，并使用字符串长度比较排序
     */
    @Test
    public void test0401() {
        System.out.println("==== 提取颜色字段属性值比较，并使用字符串长度比较，升序====");
        Comparator<String> comparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());
        appleList.sort(Comparator.comparing(Apple::getColor, comparator));
        appleList.forEach(System.out::println);
    }

    /**
     * 提取颜色字段属性值比较，并使用字符串长度比较排序
     */
    @Test
    public void test0402() {
        System.out.println("==== 提取颜色字段属性值比较，并使用字符串长度比较，降序====");
        Comparator<String> comparator = (String s2, String s1) -> Integer.compare(s1.length(), s2.length());
        appleList.sort(Comparator.comparing(Apple::getColor, comparator));
        appleList.forEach(System.out::println);
    }


    /**
     * 使用 lambda 实现比较器
     */
    @Test
    public void test05() {
        Comparator<String> comparator = (String s1, String s2) -> s1.length() - s2.length();

        List<String> data = new ArrayList<>();
        data.add("22222");
        data.add("333");
        data.add("1111");
        
        Collections.sort(data, comparator);
        data.forEach(System.out::println);

    }



}
