package com.guorong.guava.util.G_02_File;

import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * 文件处理演示
 */
public class FilesTest {

    private String file;

    @Before
    public void before() {
        file = this.getClass().getResource("/").getPath().concat("student.txt");
    }


    @Test
    public void testFileName() {
        // 获取文件名(出去扩展名)
        String nameWithoutExtension = Files.getNameWithoutExtension(file);
        Assert.assertEquals("student", nameWithoutExtension);
    }


    @Test
    public void testExtension() {
        // 获取扩展名
        String fileExtension = Files.getFileExtension(file);
        Assert.assertEquals("txt", fileExtension);
    }


    @Test
    public void test() throws IOException {
        // 复制文件
        File srcFile = new File(this.file);

        File targetFile = new File("d:/copy.txt");

        Files.copy(srcFile, targetFile);
    }


}
