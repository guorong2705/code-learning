package com.guorong.exception;

/**
 * 支付异常类
 */
public class PaymentException extends RuntimeException{

    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}
