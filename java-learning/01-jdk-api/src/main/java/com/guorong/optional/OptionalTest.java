package com.guorong.optional;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/**
 * Optional演示
 *
 * @author guorong
 */
public class OptionalTest {


    /**
     * 创建 Optional 对象:
     * 返回一个Optional容器对象，而不是 null。建议常用⭐⭐⭐⭐
     */
    @Test
    public void testEmpty() {
        Optional<String> empty = Optional.empty();
        // Optional为空的，此时报异常：NoSuchElementException
        String s = empty.get();
    }


    /**
     * 创建 Optional 对象:
     * 创建Optional对象，如果 value 是 null，则抛出 NPE。不建议用⭐⭐
     */
    @Test
    public void testOf() {
        String value = new String("123");
        // 如果 value 为 null, 这段代码会立即抛出一个 NullPointerException
        Optional<String> strOptional = Optional.<String>of(value);
    }


    /**
     * 创建 Optional 对象:
     * 创建Optional 对象，但 value 为空时返回 Optional.empty()。推荐使用⭐⭐⭐⭐⭐
     */
    @Test
    public void testNullable() {
        Optional nullOptional = Optional.ofNullable(null);
    }


    /**
     * ifPresent(Consumer)：当值存在时调用 Consumer，否则什么也不做。
     */
    @Test
    public void testIfPresent() {
        // 不存在值
        Optional<String> nullOptional = Optional.ofNullable(null);
        nullOptional.ifPresent(e -> System.out.println("存在值 " + e));

        // 存在值
        Optional<String> optional = Optional.ofNullable("Has A Value");
        optional.ifPresent(e -> System.out.println("存在值 " + e));
    }


    /**
     * 不管是是否存在值都会执行 otherObject
     * orElse(otherObject)：如果值存在则直接返回，否则返回 otherObject。
     */
    @Test
    public void testOrElse() {
        String otherObject = Optional
                .<String>ofNullable(null)
                .orElse("other");
        System.out.println(otherObject);
    }

    /**
     * 只有不存在值的时候，才会执行 Supplier 提供的函数
     * orElseGet(Supplier)：如果值存在则直接返回，否则使用 Supplier(提供者) 函数生成一个可替代对象。
     */
    @Test
    public void testOrElseGet() {
        Integer integer = Optional
                .<Integer>ofNullable(null)
                .orElseGet(() -> Integer.valueOf(30));

        System.out.println(integer);
    }


    /**
     * orElseThrow(Supplier)：如果值存在直接返回，否则使用 Supplier 函数生成一个异常。
     */
    @Test
    public void testOrElseThrow() {

        String value = Optional.<String>ofNullable("value")
                .orElseThrow(() -> new NullPointerException("value is null---"));

        System.out.println("============================");

        String valueIsNull = Optional.<String>ofNullable(null)
                .orElseThrow(() -> new NullPointerException("value is null==="));
    }


    /**
     * filter(Predicate)：
     * 将 Predicate 应用于 Optional 中的内容并返回结果。
     * 当 Optional 不满足 Predicate 时返回空。如果 Optional 为空，则直接返回。
     */
    @Test
    public void testFilter() {
        // 1. 当 Optional 不满足 Predicate 时返回空
        Optional<String> s1 = Optional
                .of("hello world")
                .filter(s -> s.startsWith("hello"));

        System.out.println(s1);

        System.out.println("===================================================");

        // 2. 如果 Optional 为空，则直接返回空, 不会调用 filter() 方法
        Optional object = Optional
                .ofNullable(null)
                .filter(s -> {
                    System.out.println("------");
                    return Objects.isNull(s);
                });
        System.out.println(object);
    }


    /**
     * map(Function)：如果 Optional 不为空，应用 Function 于 Optional 中的内容，并返回结果。否则直接返回 Optional.empty。
     */
    @Test
    public void testMap() {

        // 1. 如果 Optional 不为空，应用 Function 于 Optional 中的内容，并返回结果
        Optional<String> optional = Optional
                .of("hello world")
                .map(s -> s.toUpperCase());
        System.out.println(optional); // Optional[HELLO WORLD]

        // 2. 如果 Optional 为空，直接返回 Optional.empty
        Optional<String> result = Optional
                .ofNullable(null)
                .map(s -> {
                    System.out.println("------");
                    return "hello world";
                });
        System.out.println(result);

    }


    private class Person {
        private Optional<Car> car;

        public Optional<Car> getCar() {
            return car;
        }
    }

    private class Car {
        private Optional<Insurance> insurance;

        public Optional<Insurance> getInsurance() {
            return insurance;
        }
    }

    private class Insurance {
        private String name;

        public String getName() {
            return name;
        }
    }

    /**
     * 获取对象中的属性
     */
    @Test
    public void test02() {
        /**
         * 获取Optional中对象的属性
         */
        Insurance insurance = new Insurance();
        Optional<Insurance> insuranceOptional = Optional.<Insurance>ofNullable(insurance);// 创建可以为空的Optional对象

        // 使用 map 获取属性值
        Optional<String> nameOptional = insuranceOptional.map(Insurance::getName);

        /**
         *  map 和 flatMap 使用的对比
         */
        Optional<Person> person = Optional.ofNullable(new Person());
        // 使用 map 获取
        Optional<Optional<Car>> car1 = person.map(Person::getCar);
        // 使用 flatMap 获取
        Optional<Car> car2 = person.flatMap(Person::getCar);

    }

    /**
     * 默认行为及解引用 Optional 对象
     */
    @Test
    public void test03() {
        Insurance insurance = new Insurance();
        Optional<Insurance> optional = Optional.<Insurance>ofNullable(insurance);
        /**
         * orElse 如果有值则将其返回，否则返回一个默认值
         */
        String orElse = optional.map(Insurance::getName).orElse("Unknow");
        /**
         * orElseGet 如果有值则将其返回，否则返回一个由指定的 Supplier 接口生成的值
         */
        String s1 = optional.map(Insurance::getName).orElseGet(String::new);
        String s2 = optional.map(Insurance::getName).orElseGet(() -> "123");
        /**
         * orElseThrow 如果有值则将其返回，否则抛出一个由指定的 Supplier 接口生成的异常
         */
        String s3 = optional.map(Insurance::getName).orElseThrow(RuntimeException::new);
        String s4 = optional.map(Insurance::getName).orElseThrow(() -> new RuntimeException("value is not present"));


    }


}
