package com.guorong.lambda;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Lamdba表达式 语法
 *
 * @author guorong
 * @create 2019-12-06
 */
public class LambdaExpressionDemo {

    @Data
    private class Apple {
        private String color;
        private double weight;
    }


    @Test
    public void test(){
        /**
         * Predicate
         */
        Predicate<String> predicate1 = (String s) -> Objects.equals(s,"java");
        Predicate<String> predicate2 = s -> Objects.equals(s,"java");
        Predicate<String> predicate3 = s -> {
            return Objects.equals(s,"java");
        };

        /**
         * Comparator
         */
        Comparator<Apple> comparator1 = (apple1, apple2) -> Double.compare(apple1.getWeight(),apple2.getWeight());
        Comparator<Apple> comparator2 = Comparator.comparingDouble(Apple::getWeight);
        /**
         * Function
         */
        Function<Apple,String> function1 = apple -> apple.getColor();
        Function<Apple,String> function2 = Apple::getColor;



    }



}
