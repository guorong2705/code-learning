package com.guorong.function_interface;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


/**
 * JDK_8 自带的 Predicate<T> 函数接口演示:
 * Predicate 包括三个方法：negate、and和or,让你可以重用已有的 Predicate 来创建更复杂的谓词
 * @author guorong
 * @create 2019-11-29
 */
public class PredicateTest {

    private List<Apple> appleList = Arrays.asList(
            new Apple("red",100),
            new Apple("red",180),
            new Apple("blue",180),
            new Apple("green",100)
    );


    /**
     * 自定义过滤器
     * @param list
     * @param predicate
     * @param <T>
     * @return
     */
    private <T> List<T> filter(List<T> list, Predicate<T> predicate){
        List<T> result = new ArrayList<>();
        list.forEach(t -> {
            if (predicate.test(t)) {
                result.add(t);
            }
        });
        return result;
    }

    @Test
    public void test01 () {
        System.out.println("===获取红色苹果====");
        List<Apple> redAppleList = filter(appleList, apple -> Objects.equals(apple.getColor(), "red"));
        redAppleList.forEach(System.out::println);
    }

    @Test
    public void test02(){
        System.out.println("===获取红色且重量大于150的苹果====");
        // 红色苹果的谓词
        Predicate<Apple> redApplePredicate = apple -> Objects.equals(apple.getColor(),"red");
        // 重量大于150的谓词
        Predicate<Apple> weightApplePredicate = apple -> apple.getWeight() > 150;

        List<Apple> apples = filter(appleList, redApplePredicate.and(weightApplePredicate));
        apples.forEach(System.out::println);
    }


    /**
     * 从简单 Lambda 表达式出发，你可以构建更复杂的表达式，但读起来仍然和问题的陈述差不多！
     * 请注意: and 和 or 方法是按照在表达式链中的位置，从左向右确定优先级的。
     * 因此: a.or(b).and(c) 可以看作 (a || b) && c
     * 因此: a.and(b).or(c) 可以看作 (a && b) || c
     */
    @Test
    public void test03(){
        System.out.println("===获取红色且重量大于150的苹果 或者 绿苹果====");
        // 红色苹果的谓词
        Predicate<Apple> redApplePredicate = apple -> Objects.equals(apple.getColor(),"red");
        // 重量大于150的谓词
        Predicate<Apple> weightApplePredicate = apple -> apple.getWeight() > 150;
        // 绿色苹果谓词
        Predicate<Apple> greenApplePredicate = apple -> Objects.equals(apple.getColor(), "green");
        // 获取红色且重量大于150的苹果 或者 绿苹果
        List<Apple> apples = filter(appleList, redApplePredicate.and(weightApplePredicate).or(greenApplePredicate));
        apples.forEach(System.out::println);

    }






}
