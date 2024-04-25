package com.guorong.springframwork.core.io;

import java.util.Objects;

/**
 * 默认资源加载器实现
 */
public class DefaultResourceLoader implements ResourceLoader {

    private static final String CLASSPATH_URL_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {
        if (Objects.isNull(location) || location.trim().length() == 0) {
            throw new IllegalArgumentException("location is not null");
        }
        // 判断是否为类路径
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClasPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        throw new UnsupportedOperationException(String.format("location={%s} is not load", location));
    }

    public static void main(String[] args) {
        String s = "classpath:2546454d4fdfd";
        System.out.println(s.startsWith(CLASSPATH_URL_PREFIX));
    }
}
