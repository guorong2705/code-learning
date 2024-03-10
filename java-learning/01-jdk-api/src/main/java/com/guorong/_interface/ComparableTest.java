package com.guorong._interface;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 1、Arrays.sort()和Collections.sort()方法可以容器中的元素进行排序，但要求对象所属的类必须实现了 Comparable 接口。
 * 2、Comparable接口中的compareTo方法将返回一个整型数值。如果两个对象不相等，则返回一个正值或者一个负值。在对两个整数域进行比较时，这点非常有用。
 * @author guorong
 * @date 2021-08-03
 */
public class ComparableTest {

    @Data
    private class Person implements Comparable<Person>{

        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person o) {
            // 通过 age 字段比较
           return Integer.compare(this.getAge(), o.getAge());
        }
    }

    @Test
    public void testSort() {
        Random random = new Random(60);
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Person("张三", random.nextInt(20)));
        }
        list.forEach(System.out::println);

        System.out.println("=============================================");

        Collections.sort(list);
        list.forEach(System.out::println);

    }



}
