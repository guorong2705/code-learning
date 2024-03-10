package com.guorong._collection;


import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ArrayListTest {

    @Test
    void test01() {
        ArrayList<String> arrayList = new ArrayList<>(100);
        for (int i = 0; i < 3; i++) {
            arrayList.add(String.valueOf(i));
        }
        arrayList.add(3, "001");
        String[] array = {"11", "11", "11", "1", "1", "1", "1"};
        Arrays.stream(array).forEach(System.out::println);
    }


    @Test
    void testSerialize() throws IOException, ClassNotFoundException {
        String filePath = getClass().getClassLoader().getResource("arrayList.txt").getPath();
        // 序列化集合
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
        List<String> arrayList01 = Arrays.asList("001", "002", "003");
        objectOutputStream.writeObject(arrayList01);
        objectOutputStream.close();
        // 反序列化集合
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
        List<String> readList = (List<String>) objectInputStream.readObject();
        System.out.println(readList);
    }

}
