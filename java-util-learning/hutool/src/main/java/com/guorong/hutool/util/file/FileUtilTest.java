package com.guorong.hutool.util.file;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author guorong
 * @date 2021-09-19
 */
public class FileUtilTest {


    @Test
    public void testGetMimeType() {
        List<String> fileNameList = Arrays.asList("01.jpg", "always.mp4", "周报.xlsx");
        fileNameList.forEach(filename -> System.out.println(FileUtil.getMimeType(filename)));
    }


}
