package com.guorong.function_interface;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Consumer<T> 消费数据
 * @author guorong
 * @create 2019-11-29
 */
public class ConsumeTest {

    private List<Apple> appleList = Arrays.asList(
            new Apple("red",13),
            new Apple("blue", 12),
            new Apple("yellow",18)
    );

    /**
     * 消费数据
     * void accept(T t);
     */
    private <T> void consume(List<T> list, Consumer<T> consumer){
        list.forEach((T t) -> {
            consumer.accept(t);
        });
    }


    @Test
    public void test01(){
        System.out.println("==============使用Lambda表达式 打印苹果的颜色 ====================");
        consume(appleList, apple -> System.out.println(apple.getColor()));

        System.out.println("===============使用方法引用 打印苹果对象 ================");
        consume(appleList, System.out::println);
    }





}
