package com.guorong.springframwork.core.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * 资源加载器测试
 */
public class ResourceLoaderTests {

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Test
    public void test() throws IOException {
        String location = "classpath:com/guorong/springframwork/core/io/DefaultResourceLoader.class";
        Resource resource = resourceLoader.getResource(location);
        System.out.println(resource.getInputStream());
    }
}
