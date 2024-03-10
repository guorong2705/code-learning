package com.guorong.path;

import org.junit.jupiter.api.Test;

/**
 * @author guorong
 * @date 2021-09-02
 */
public class MyGetResourceTest {

    private String fileName = "file.txt";

    /**
     * 获取Classpath路径
     */
    @Test
    public void test01() {
        String path = getClass().getResource("/").getPath().concat(fileName);
        //  /E:/learning/java-learning/01-jdk-api/target/classes/file.txt
        System.out.println(path);
    }

    /**
     * 获取Classpath路径
     */
    @Test
    public void test02() {
        String path = getClass().getClassLoader().getResource("").getPath().concat(fileName);
        //  /E:/learning/java-learning/01-jdk-api/target/classes/file.txt
        System.out.println(path);
    }








}
