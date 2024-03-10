package com.guorong.common;

import lombok.Getter;

import java.io.Serializable;

/**
 * 统一返回结果
 *
 * @param <T>
 */
@Getter
public class CommonResult<T> implements Serializable {
    private Integer code;
    private Boolean success;
    private String message;
    private T data;

    private CommonResult(Integer code, Boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), true, ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功
     *
     * @return
     */
    public static CommonResult success() {
        return success(null);
    }

    /**
     * 失败
     *
     * @param resultCode
     * @return
     */
    public static CommonResult fail(ResultCode resultCode) {
        return new CommonResult(resultCode.getCode(), false, resultCode.getMessage(), null);
    }


}
