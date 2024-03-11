package com.guorong.resp;

import com.guorong.enums.ResultCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * 统一返回对象
 *
 * @param <T>
 */
@Getter
public final class ResultData<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    private long timestamp;

    private ResultData(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功
     * @return
     */
    public static ResultData success() {
        return new ResultData(ResultCode.RC_200.getCode(), ResultCode.RC_200.getName(), null);
    }

    /**
     * 成功
     * @param data 响应数据
     * @return
     * @param <T> 相应数据类型
     */
    public static <T> ResultData<T> success(T data) {
        return new ResultData(ResultCode.RC_200.getCode(), ResultCode.RC_200.getName(), data);
    }


    /**
     * 失败
     * @return
     */
    public static ResultData fail() {
        return new ResultData(ResultCode.RC_500.getCode(), ResultCode.RC_500.getName(), null);
    }

    /**
     * 失败
     * @param resultCode 失败编码枚举类
     * @return
     */
    public static ResultData fail(ResultCode resultCode) {
        return new ResultData(resultCode.getCode(), resultCode.getName(), null);
    }


}
