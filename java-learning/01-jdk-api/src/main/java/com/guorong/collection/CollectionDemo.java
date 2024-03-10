package com.guorong.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Collection演示
 */
public class CollectionDemo {


    /**
     * 测试添加元素到集合中:
     * Collections.addAll() 运行得更快，而且很容易构建一个不包含元素的 Collection，因此这是首选方式。
     * Collection.addAll() 方法只能接受另一个 Collection 作为参数，因此它没有 Arrays.asList() 或 Collections.addAll() 灵活。
     * 这两个方法都使用可变参数列表。
     */
    @Test
    public void testAddAll() {
        List<String> list = new ArrayList<>();
        // 添加集合
        list.addAll(Arrays.asList("C","D","E"));
        // 使用集合工具类添加集合
        Collections.addAll(list, "F", "G", "H");
    }




}
