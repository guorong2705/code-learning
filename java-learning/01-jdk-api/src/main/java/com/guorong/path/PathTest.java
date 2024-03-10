package com.guorong.path;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * Path类测试
 */
public class PathTest {

    private String fileName = "path.txt";


    @Test
    public void test01() {
        Path path = Paths.get("d:/a.txt");
        boolean exists = Files.exists(path);
        System.out.println(exists);
    }

    @Test
    public void test02() throws IOException {
        Path path = Paths.get("a.txt");
        if (Files.exists(path)) {
            return;
        }
        // 创建项目路径下的文件
        Files.createFile(path);
    }

    @Test
    public void test03() throws IOException {
        // 获取文件Path
        String filePath = getClass().getResource("/").getPath().concat(fileName);
        Path path = new File(filePath).toPath();
        if (Files.exists(path)) {
            // 读取文件内容
            List<String> lineList = Files.readAllLines(path);
            lineList.forEach(System.out::println);
        }
    }



    @Test
    public void test05() throws IOException {
        // 获取Path
        String filePath = getClass().getResource("/").getPath().concat(fileName);
        Path path = new File(filePath).toPath();
        BufferedReader reader = Files.newBufferedReader(path);
        String line;
        while (Objects.nonNull(line = reader.readLine())) {
            System.out.println(line);
        }
        reader.close();
    }






}
