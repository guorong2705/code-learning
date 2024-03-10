package com.guorong.dict;


import com.guorong.dict.enums.Dict;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据字典注解：要使用注解翻译字典值，需要枚举类实现接口 Dict
 * @see Dict
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dictionary {
    /**
     * 字典编码所在枚举
     * @return
     */
    Class<? extends Enum<? extends Dict>> dictCodeType();
}
