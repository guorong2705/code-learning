package com.guorong.utils;

/**
 * 校验工具类
 */
public final class ValidateUtils {

    private ValidateUtils() {}

    /**
     * 校验参数
     *
     * @param condition 条件
     * @param message 条件不满足，抛出异常信息
     * @throws IllegalArgumentException condition 为true抛出
     */
    public static void validateArgument(boolean condition, String message) {
        if (condition) {
            throw new IllegalArgumentException(message);
        }
    }


}
