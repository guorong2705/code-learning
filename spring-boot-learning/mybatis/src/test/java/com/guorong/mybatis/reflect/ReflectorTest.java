package com.guorong.mybatis.reflect;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.Reflector;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * @ClassName: ReflectorTest
 * @Description: TODO
 * @Author: 89019586
 * @Date: 2022/9/14 17:38
 * Copyright(C),2019-2022,丰图科技有限公司
 */
@Slf4j
public class ReflectorTest {

    public static class DemoDto implements Serializable {
        private String name;
        private String age;
        public String getName() {
            return name;
        }
        public String getAge() {
            return age;
        }
    }

    @Test
    public void test() {
        System.out.println(DemoDto.class.getTypeName());
        Reflector reflector = new Reflector(DemoDto.class);
        Constructor<?> constructor = reflector.getDefaultConstructor();
        log.info("constructor --->>> {}", constructor.getName());
    }

}
