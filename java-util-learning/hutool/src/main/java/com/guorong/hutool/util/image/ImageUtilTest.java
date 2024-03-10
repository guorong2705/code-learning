package com.guorong.hutool.util.image;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.codec.Base64Encoder;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ImageUtilTest {

    @Test
    public void test() {
        File file = new File("E:/aa9faf91f2c01c02cf216fd24c1705de.jpg");
        String encode = Base64.encode(file);
        System.out.println(encode);
    }
}
