package com.guorong.validation.enum_validate.enums;

import lombok.SneakyThrows;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验枚举需要实现 EnumValidInterface 接口
 * 校验枚举值使用方法
 * - @EnumValue(enumClass = SizeEnum.class)
 * - private String size;
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumValid.EnumValidValidator.class)
public @interface EnumValid {

    Class<? extends EnumValidInterface> enumClass();

    String message() default "参数不匹配:{messageVariable}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 自定义约束校验器
     */
    class EnumValidValidator implements ConstraintValidator<EnumValid, String> {

        private Map<String, String> codeDescMap;

        // 注解 @EnumValid 在多少个地方使用，就会调用这个初始化方法多少次。说明：即同一个对象同一个字段，多次请求校验，只会在一次校验的时候调用
        @SneakyThrows
        @Override
        public void initialize(EnumValid annotation) {
            EnumValidInterface[] enumConstants = annotation.enumClass().getEnumConstants();
            if (Objects.isNull(enumConstants)) {
                throw new IllegalArgumentException("enumClass 参数错误非枚举类型(实现EnumValidInterface接口)");
            }
            // 初始化编码和描数
            codeDescMap = Arrays.stream(enumConstants)
                    .collect(Collectors.toMap(EnumValidInterface::getCode, EnumValidInterface::getName));
        }

        // 这个校验方法，每次校验都会调用
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (Objects.nonNull(value) && codeDescMap.containsKey(value)) {
                return true;
            }
            // 创建创建消息模板
            String defaultTemplate = context.getDefaultConstraintMessageTemplate();
            String replaceTemplate = defaultTemplate.replaceAll("\\{messageVariable}", codeDescMap.toString());
            // 禁用默认的 message 的值
            context.disableDefaultConstraintViolation();
            // 重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(replaceTemplate).addConstraintViolation();
            return false;
        }
    }


}
