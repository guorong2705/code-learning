package com.guorong.springframwork.beans;

/**
 * bean异常类
 */
public class BeansException extends Exception{

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
