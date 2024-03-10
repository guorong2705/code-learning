package com.guorong.mvc.inteceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD})
@Retention(RUNTIME)
@Documented
public @interface IsLogin {
    /**
     * 是否需要验证登录：默认为true，需要验证
     * @return
     */
    boolean isLogin() default true;
}
