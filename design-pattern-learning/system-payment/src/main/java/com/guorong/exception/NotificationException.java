package com.guorong.exception;

/**
 * 通知异常类
 */
public class NotificationException extends RuntimeException{

    public NotificationException(String message) {
        super(message);
    }

    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
