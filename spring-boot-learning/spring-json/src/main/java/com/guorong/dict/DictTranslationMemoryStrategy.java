package com.guorong.dict;

import cn.hutool.core.util.ClassUtil;
import com.guorong.dict.enums.Dict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 从数据库翻译
 */
@Service
public class DictTranslationMemoryStrategy implements DictTranslationStrategy {

    private static Map<Class<? extends Enum<? extends Dict>>, Map<String, String>> dictCacheMap = new HashMap<>();

    static {
        // 扫描获取指定包下的所有Class对象集合
        Set<Class<?>> classes = ClassUtil.scanPackage(ClassUtil.getPackage(Dict.class));
        for (Class<?> aClass : classes) {
            // 判断是否为实现Dict接口，且为枚举的Class对象
            if (Dict.class.isAssignableFrom(aClass) && Enum.class.isAssignableFrom(aClass)) {
                Object[] enumConstants = aClass.getEnumConstants(); // 获取枚举所有实例
                Map<String, String> code2NameMap = Arrays.stream(enumConstants)
                        .map(e -> (Dict) e)
                        .collect(Collectors.toMap(Dict::getCode, Dict::getName));
                dictCacheMap.put((Class<? extends Enum<? extends Dict>>) aClass, code2NameMap);
            }
        }
    }

    @Override
    public String translate(Class<? extends Enum<? extends Dict>> dictEnumClass, String code) {
        Assert.notNull(dictEnumClass, "dictEnumClass is not null");
        Assert.notNull(code, "code is not null");
        Map<String, String> code2NameMap = dictCacheMap.get(dictEnumClass);
        if (CollectionUtils.isEmpty(code2NameMap)) {
            return null;
        }
        return code2NameMap.get(code);
    }
}
