package com.guorong.exception;

/**
 * 配置文件解析错误
 */
public class ConfigParserException extends RuntimeException{

    public ConfigParserException(String message) {
        super(message);
    }

    public ConfigParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
