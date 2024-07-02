package com.guorong.write.util;

public final class ClassPathUtils {

    /**
     * 获取类路径
     * @return
     */
    public static String getClassPath() {
        return Thread.currentThread().getContextClassLoader().getResource(".").getPath();
    }
}
