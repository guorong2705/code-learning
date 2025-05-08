package com.guorong.utils;

/**
 * 字符串工具类
 */
public final class StringUtils {

    private StringUtils() {}

    /**
     * 判断字符串是否为空
     * @param str 要判断的字符串
     * @return 字符串为空返回true，规则返回false
     */
    public static boolean isStrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
