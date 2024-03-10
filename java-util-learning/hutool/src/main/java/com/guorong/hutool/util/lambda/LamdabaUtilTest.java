package com.guorong.hutool.util.lambda;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.func.LambdaUtil;
import lombok.Data;
import lombok.Getter;
import org.junit.jupiter.api.Test;

/**
 * @ClassName: LamdabaUtilDemo
 * @Description: TODO
 * @Author: ft89019586
 * @Date: 2022/11/21 11:26
 * Copyright(C),2019-2022,丰图科技有限公司
 */
public class LamdabaUtilTest {

    @Data
    static class Person {
        private String name;
        private Integer age;
    }

    @Test
    public void test() {
        String fieldName = LambdaUtil.getFieldName(Person::getName);
        System.out.println(fieldName);
    }
}
