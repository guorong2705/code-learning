package com.guorong.upload.download.common;

import lombok.Getter;

/**
 * 结果类响应码枚举
 *
 * @author guorong
 * @date 2020-04-08
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(Boolean.TRUE, 200, "操作成功"),
    REQUEST_PARAM_ERROR(Boolean.FALSE, 400, "参数错误"),
    FORBIDDEN(Boolean.FALSE, 403, "没有相关权限"),
    NOT_FOUND(Boolean.FALSE, 404, "资源不存在"),
    FAIL(Boolean.FALSE, 500, "操作失败"),
    NULL_POINT(Boolean.FALSE, 800, "空指针异常");

    /**
     * 响应是否成功
     */
    private final Boolean success;
    /**
     * 响应状态码
     */
    private final Integer code;
    /**
     * 响应信息
     */
    private final String message;

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

}
