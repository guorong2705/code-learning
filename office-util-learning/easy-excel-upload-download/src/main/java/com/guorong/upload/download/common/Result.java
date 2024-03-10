package com.guorong.upload.download.common;


import lombok.Getter;

/**
 * 统一API响应结果封装
 *
 * @author guorong
 * @date 2020-04-08
 */
@Getter
public class Result<T> {

    /**
     * 响应是否成功
     */
    private Boolean success;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 返回的数据
     */
    private T data;


    public Result(Boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //-----------------------成功----------------------------------------------

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示的信息
     * @return
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<T>(ResultCodeEnum.SUCCESS.getSuccess(), ResultCodeEnum.SUCCESS.getCode(), message, data);
    }


    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultCodeEnum.SUCCESS.getSuccess(), ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @return
     */
    public static Result success() {
        return Result.success(null);
    }


    //---------------------------失败----------------------------------------------

    /**
     * 失败返回结果
     *
     * @param code    响应状态码
     * @param message 提示信息
     * @return
     */
    public static Result fail(Integer code, String message) {
        return new Result(ResultCodeEnum.FAIL.getSuccess(), code, message, null);
    }


    /**
     * 失败返回结果
     *
     * @param resultCodeEnum 结果类枚举
     * @return
     */
    public static Result fail(ResultCodeEnum resultCodeEnum) {
        return Result.fail(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

    /**
     * 失败
     *
     * @param message 提示信息
     * @return
     */
    public static Result fail(String message) {
        return Result.fail(ResultCodeEnum.FAIL.getCode(), message);
    }


    /**
     * 失败返回结果
     *
     * @return
     */
    public static Result fail() {
        return fail(ResultCodeEnum.FAIL);
    }


}
