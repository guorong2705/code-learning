package com.guorong.hutool.util.file;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * IO工具类
 */
public class IoUtilTest {


    /**
     * 读取txt文件内容
     */
    @Test
    public void testReadLines() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("country.txt");
        ArrayList<String> lines = IoUtil.readLines(inputStream, StandardCharsets.UTF_8, CollectionUtil.newArrayList());
        lines.forEach(System.out::println);
    }


}
