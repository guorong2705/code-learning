package com.guorong.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用 LinkedHashMap 实现 LRU 缓存机制
 */
public class LinkedHashMap_Demo {

    private static LinkedHashMap<String,String> map = new LinkedHashMap<String,String>(16, 0.75F,true) {

        /**
         * 重写 removeEldestEntry 方法定义淘汰元素时需满足的条件
         * @param eldest
         * @return
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return super.removeEldestEntry(eldest);
        }
    };

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            map.put("key-"+i, "value-" + i);
        }

        map.forEach((key,value) -> System.out.println(key + "=" + value));

        System.out.println("================================");
        map.get("key-3");
        map.forEach((key,value) -> System.out.println(key + "=" + value));


    }



}
