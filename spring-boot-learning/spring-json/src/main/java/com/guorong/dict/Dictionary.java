package com.guorong.dict;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.guorong.dict.enums.Dict;
import com.guorong.dict.vo.UserVo;
import com.guorong.util.SpringContextUtils;
import org.springframework.beans.BeanWrapperImpl;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典翻译注解：要使用注解翻译字典值，需要枚举类实现接口 Dict。
 *<p>
 *  在需要字段上如下使用：
 * <pre>
 * &#64;Dictionary(dictCodeType = StatusType.class, codeField = "status")
 * private String statusName = Dictionary.DEFAULT_DIC_VALUE;
 * </pre>
 * @see Dict
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JsonSerialize(using = Dictionary.DictionarySerializer.class)
@JacksonAnnotationsInside // 组合jackson注解
public @interface Dictionary {

    String DEFAULT_DIC_VALUE = "default_dic_value";

    /**
     * 字典编码所在枚举
     *
     * @return
     */
    Class<? extends Enum<? extends Dict>> dictCodeType();

    String codeField();


    /**
     * 字典序列化器
     */
    class DictionarySerializer extends JsonSerializer<String> implements ContextualSerializer {

        // 字典编码
        private Class<? extends Enum<? extends Dict>> dictCodeType;

        private String codeField;

        public DictionarySerializer() {
        }

        public DictionarySerializer(Class<? extends Enum<? extends Dict>> dictCodeType, String codeField) {
            this.dictCodeType = dictCodeType;
            this.codeField = codeField;
        }


        @Override
        public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
            // 获取指定字典注解
            Dictionary annotation = property.getAnnotation(Dictionary.class);
            return new DictionarySerializer(annotation.dictCodeType(), annotation.codeField());
        }

        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            BeanWrapperImpl beanWrapper = new BeanWrapperImpl(gen.getCurrentValue());
            Object codeFieldValue = beanWrapper.getPropertyValue(codeField);
            // 翻译编码
            DictTranslationStrategyFactory strategyFactory = SpringContextUtils.getBean(DictTranslationStrategyFactory.class);
            DictTranslationStrategy dictTranslationStrategy = strategyFactory.instanceDictStrategy();
            String translateValue = dictTranslationStrategy.translate(dictCodeType, String.valueOf(codeFieldValue));
            // 写出翻译后的值
            gen.writeObject(translateValue);
        }

        public static void main(String[] args) {
            UserVo userVo = new UserVo();
            userVo.setUserName("张三");
            BeanWrapperImpl beanWrapper = new BeanWrapperImpl(userVo);
            Object userName = beanWrapper.getPropertyValue("userName");
            System.out.println(userName);
        }
    }
}
