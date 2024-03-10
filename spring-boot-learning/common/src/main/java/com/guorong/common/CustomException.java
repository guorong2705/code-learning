package com.guorong.common;

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
