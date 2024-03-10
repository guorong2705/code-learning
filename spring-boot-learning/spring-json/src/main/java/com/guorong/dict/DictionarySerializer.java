package com.guorong.dict;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.guorong.dict.enums.Dict;
import com.guorong.util.SpringContextUtils;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 字典自定义序列化器
 */
public class DictionarySerializer extends JsonSerializer<String> implements ContextualSerializer {

    // 字典编码
    private Class<? extends Enum<? extends Dict>> dictCodeType;

    private String fieldName;

    public DictionarySerializer() {
    }

    public DictionarySerializer(Class<? extends Enum<? extends Dict>> dictCodeType, String fieldName) {
        this.dictCodeType = dictCodeType;
        this.fieldName = fieldName;
    }


    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        // 获取指定字典注解
        Dictionary annotation = property.getAnnotation(Dictionary.class);
        // 注解标志的字段名称
        String fieldName = property.getName();
        return new DictionarySerializer(annotation.dictCodeType(), fieldName);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        // 编码
        gen.writeObject(value);
        // 编码中文名称
        DictTranslationStrategyFactory strategyFactory = SpringContextUtils.getBean(DictTranslationStrategyFactory.class);
        DictTranslationStrategy dictTranslationStrategy = strategyFactory.instanceDictStrategy();
        String translateValue = dictTranslationStrategy.translate(dictCodeType, value);
        gen.writeStringField(fieldName + "Name", translateValue);
    }
}

