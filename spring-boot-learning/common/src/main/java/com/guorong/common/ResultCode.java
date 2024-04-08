package com.guorong.common;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum ResultCode implements Serializable {
    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    ;
    private final Integer code;

    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
