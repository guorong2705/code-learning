package com.guorong.function_interface;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Function: Class To Class，将一种类型转换为另一种类型
 * Function接口为此配了andThen和compose两个默认方法，它们都会返回 Function 的一个实例
 *
 * @author guorong
 * @create 2019-11-29
 */
public class FunctionTest {


    /**
     * andThen 方法会返回一个函数，它先对输入应用一个给定函数，再对输出应用另一个函数。
     * 比如，假设有一个函数 f 给数字加1 (x -> x + 1) ，另一个函数 g 给数字乘2，
     * 你可以将它们组合成一个函数 h ，先给数字加 1，再将结果乘 2。
     */
    @Test
    public void testAndThen(){
        Function<Integer,Integer> f = i -> i + 1;

        Function<Integer,Integer> g = i -> i * 2;

        Function<Integer, Integer> h = f.andThen(g);

        Integer result = h.apply(1); // result = 4
    }

    /**
     * 你也可以类似地使用 compose 方法，先把给定的函数用作 compose 的参数里面给的那个函
     * 数，然后再把函数本身用于结果。比如在上一个例子里用 compose 的话，它将意味着 f(g(x)) ，
     * 而 andThen 则意味着 g(f(x)) ：
     */
    @Test
    public void testCompose(){
        Function<Integer,Integer> f = i -> i + 1;
        Function<Integer,Integer> g = i -> i * 2;
        Function<Integer, Integer> h = f.compose(g);
        Integer result = h.apply(1); // result = 3
    }


    /**
     * 类型转换: List<T> => List<R>
     * @param list
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    private <T,R> List<R> map(List<T> list, Function<T,R> function){
        List<R> result = new ArrayList<>();
        for(T t : list){
            R r = function.apply(t);
            result.add(r);
        }
        return result;
    }

    /**
     * 测试 Function类的 R apply(T t);
     */
    @Test
    public void test(){

        List<String> list = Arrays.asList("1", "2", "3","4");

        System.out.println("====  使用Lambda表达式  ====");
        List<Integer> integers1 = map(list, s -> Integer.parseInt(s));
        integers1.forEach(System.out::println);

        System.out.println("====  使用方法引用  ====");
        List<Integer> integers2 = map(list, Integer::parseInt);
        integers2.forEach(System.out::println);


    }


}
