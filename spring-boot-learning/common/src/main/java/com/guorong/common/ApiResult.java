package com.guorong.common;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ApiResult<T> implements Serializable {
    private static final Integer SUCCESS_CODE = 20000;
    private static final Integer FAIL_CODE = 50000;
    private static final String SUCCESS_MESSAGE = "成功";
    private static final String FAIL_MESSAGE = "失败";

    private Integer code;

    private String message;

    private T data;

    private ApiResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResult success() {
        return new ApiResult(SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<T>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static ApiResult fail() {
        return new ApiResult(FAIL_CODE, FAIL_MESSAGE, null);
    }

    public static ApiResult fail(String message) {
        return new ApiResult(FAIL_CODE, message, null);
    }

    public static <T> ApiResult<T> fail(T data) {
        return new ApiResult<T>(FAIL_CODE, FAIL_MESSAGE, data);
    }

    public static <T> ApiResult<T> fail(String message, T data) {
        return new ApiResult<T>(FAIL_CODE, message, data);
    }

}
