package com.guorong.mvc.log_aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义日志注解
 * @author guorong
 * @date 2021-06-05
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLog {

    /**
     *  一级模块
     * @return
     */
    String firstModule() default "";

    /**
     * 二级模块
     * @return
     */
    String secondModule() default "";

    /**
     * 操作类型
     * @return
     */
    String actionType() default "";


}
