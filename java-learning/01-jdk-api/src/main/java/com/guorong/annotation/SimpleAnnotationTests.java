package com.guorong.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 注解演示
 */
public class SimpleAnnotationTests {

    @Test
    public void test01() {
        Method[] methods = MyClass.class.getDeclaredMethods();
        for (Method method : methods) {
            UseCase annotation = method.getAnnotation(UseCase.class);
            if (Objects.nonNull(annotation)) {
                System.out.println(String.format("%s  value=%s  description=%s", method.getName(), annotation.value(), annotation.description()));
            }
        }
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface UseCase {
    String value();
    String description() default "defaultDescription";
}

class MyClass {

    @UseCase("001")
    public void method01() {}

    @UseCase(description = "描数",value = "002")
    public void method02(){}

    public void method03(){}
}