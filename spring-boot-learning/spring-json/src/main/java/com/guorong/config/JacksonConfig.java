package com.guorong.config;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.guorong.dict.Dictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RequiredArgsConstructor
@Configuration
public class JacksonConfig {

    // 时区
    private static final String TIME_ZONE = "Asia/Shanghai";

    // 日期时间格式
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return (Jackson2ObjectMapperBuilder builder) -> {
            // 时区
            builder.timeZone(TIME_ZONE);
            // 旧日期格式
            builder.simpleDateFormat(DATE_TIME_FORMAT);
            // JDK8的日期 -->> 不带时分秒
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
            // JDK8的日期 -->> 带时分秒
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
            // AnnotationIntrospector(Jackson库中的一个重要类，它用于处理注解，以便在序列化和反序列化过程中对Java对象进行定制处理)
            // builder.annotationIntrospector(dictNopAnnotationIntrospector());
        };
    }

    private JacksonAnnotationIntrospector dictNopAnnotationIntrospector() {
        return new JacksonAnnotationIntrospector() {
            @Override
            public Object findSerializer(Annotated annotated) {
                // 字段存在自定义的 @Dictionary注解, 使用DictionarySerializer序列化器进行序列化
                Dictionary dictionaryAnnotation = annotated.getAnnotation(Dictionary.class);
                if (Objects.nonNull(dictionaryAnnotation)) {
                    return Dictionary.DictionarySerializer.class;
                }
                // 使用默认初始化的序列化器
                return super.findSerializer(annotated);
            }
        };
    }
}
