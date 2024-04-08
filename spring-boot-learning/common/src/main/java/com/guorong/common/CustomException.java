package com.guorong.common;

/**
 * 自定义异常
 */
public class CustomException extends RuntimeException {

    public CustomException() {
    }
    public CustomException (String message) {
        super(message);
    }

    public CustomException(Throwable throwable) {
        super(throwable);
    }
}
