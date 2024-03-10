package com.guorong.try_with_resource.demo;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * try-with-resource 测试类
 * @author guorong
 * @create 2019-11-29
 */
public class TryWithResourceTest {

    private Path srcPath = Paths.get("D:/resource.txt");
    private Path destPath = Paths.get("D:/copy.txt");

    /**
     * jdk_7后使用 try-with-resource 自动关闭IO流,
     * 当跳出 try-with-resource 自动调用实现了 Closeable 的 close() 方法
     */
    @Test
    public void test() {
        try (
                BufferedReader reader = Files.newBufferedReader(srcPath);
                BufferedWriter writer = Files.newBufferedWriter(destPath);
        ) {
            String line = null;
            while((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
