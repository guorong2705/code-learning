package com.guorong._collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CollectionsTest {

    @Test
    public void testCheckedList() {
        List list01 = new ArrayList();
        list01.add("11");
        list01.add(Integer.valueOf(12));

        System.out.println("===============================");

        List list02 = Collections.checkedList(new ArrayList(), String.class);
        list02.add("11");
        list02.add(Integer.valueOf(12)); // java.lang.ClassCastException
    }


    @Test
    public void testSortAndShuffle() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(4);
        list.add(8);
        list.add(6);
        // [5, 4, 8, 6]
        System.out.println(list);
        // 进行排序
        Collections.sort(list);
        // [4, 5, 6, 8]
        System.out.println(list);
        // 打乱顺序
        Collections.shuffle(list, new Random(47));
        // [4, 5, 8, 6]
        System.out.println(list);
    }



}
