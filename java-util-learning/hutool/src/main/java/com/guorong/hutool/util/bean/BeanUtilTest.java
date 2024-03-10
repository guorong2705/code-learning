package com.guorong.hutool.util.bean;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;


import java.util.*;

/**
 * Bean工具-BeanUtil
 *
 * @author guorong
 */
class BeanUtilTest {


    /**
     * BeanUtil复制全部的属性,包括属性值为 NULL
     */
    @Test
    public void copyAllProp() {
        Person source = new Person("张三001", "张三", null);
        Person target = new Person("李四002", "李四", 27);
        // 复制全部属性
        BeanUtil.copyProperties(source, target);
        System.out.println("复制全部属性 = " + target);
    }


    /**
     * BeanUtil复制属性的时, 忽略属性值为 null 的属性
     */
    @Test
    void copyIgnoreNullProp() {
        Person source = new Person("张三001", "张三", null);
        Person target = new Person("李四002", "李四", 27);
        // 复制属性忽略 null 值
        CopyOptions ignoreNullValue = CopyOptions.create().ignoreNullValue();
        BeanUtil.copyProperties(source, target, ignoreNullValue);
        System.out.println("忽略 null 的属性 = " + target);
    }


    /**
     * 复制对象属性的时,如果对象的属性是个对象，也会复制属性对象中的值
     */
    @Test
    void copyPropIsObject() {
        // 源数据
        Person source = new Person("001", "张三", 23);
        source.setCar(new Car("宝马01", Double.valueOf(23.23)));
        // 目标数据
        Person target = new Person("002", "李四", 24);
        target.setCar(new Car("奔驰", Double.valueOf(24.24)));
        // 复制
        BeanUtil.copyProperties(source, target);
        System.out.println("复制数据 -> " + target);
    }


    /**
     * 使用 map 填充 bean
     */
    @Test
    void fillBeanWithMap() {
        // map数据
        Map<String,Object> map = new HashMap<>();
        map.put("id", "A001");
        map.put("name", "张三");
        map.put("age", 26);
        // 填充数据
        Person person = BeanUtil.fillBeanWithMap(map, new Person(), false);
        // Person(id=A10001, name=张三, age=26, car=null)
        System.out.println(person);
    }


    /**
     * bean 转为 map
     */
    @Test
    void beanToMap() {
        Person person = new Person();
        person.setId("A001");
        person.setName("张三");
        person.setAge(25);
        person.setCar(new Car("宝马", 25.0));
        // 转换为 map 数据
        Map<String, Object> map = BeanUtil.beanToMap(person);
        // {id=A001, name=张三, age=25, car=Car(brand=宝马, price=25.0)}
        System.out.println(map);
    }


    @Test
    void test() {
        Number str = 12;
        System.out.println(str.getClass() == Number.class);
    }


    /**
     * 替换集合中元素的属性值"null"字符串
     */
    @Test
    public void beanToMap01() {
        List<Person> list = Arrays.asList(
                new Person("A001null", "张三", null),
                new Person("A002nul", "null", null)
        );
        // 替换集合中元素的属性值"null"字符串
        list = replaceListElementPropertyNullStr(list);
        list.forEach(System.out::println);
    }


    /**
     * 替换集合中元素对象属性值中的 "null" 字符串
     * @param list
     * @param <T>
     * @return
     */
    private <T> List<T> replaceListElementPropertyNullStr(List<T> list) {
        List<T> resultList = CollectionUtil.newArrayList();
        if (CollectionUtil.isEmpty(list)) {
            return resultList;
        }
        for (T element : list) {
            resultList.add(replaceNullStr(element));
        }
        return resultList;
    }

    /**
     * 替换字符串中的 "null" 字符串
     * @param type
     * @param <T>
     * @return
     */
    private <T> T replaceNullStr(T type) {
        Map<String, Object> beanMap = BeanUtil.beanToMap(type);
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            Object value = entry.getValue();
            if (String.class.isInstance(value)) {
                String newValue = StrUtil.replace(String.valueOf(value), "null", "");
                beanMap.put(entry.getKey(), newValue);
            }
        }
        return (T)BeanUtil.toBean(beanMap, type.getClass());
    }


}
