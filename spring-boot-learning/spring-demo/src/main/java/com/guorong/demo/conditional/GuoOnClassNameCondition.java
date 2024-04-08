package com.guorong.demo.conditional;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 判断条件类
 */
@Slf4j
public class GuoOnClassNameCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ClassLoader classLoader = context.getClassLoader();
        Map<String, Object> annotationAttributes =
                metadata.getAnnotationAttributes(GuoConditionalOnClassName.class.getName());
        // 获取类名称
        String className = String.valueOf(annotationAttributes.get("value"));
        try {
            classLoader.loadClass(className);
            return true;
        } catch (ClassNotFoundException e) {
            log.info("class name not found --->>> {}", className);
            return false;
        }

    }
}
