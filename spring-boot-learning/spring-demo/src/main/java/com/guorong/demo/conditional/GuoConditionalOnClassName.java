package com.guorong.demo.conditional;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 自定义条件注解，根据类名称进行注册Bean
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(value = GuoOnClassNameCondition.class)
public @interface GuoConditionalOnClassName {

    String value() default "";
}
