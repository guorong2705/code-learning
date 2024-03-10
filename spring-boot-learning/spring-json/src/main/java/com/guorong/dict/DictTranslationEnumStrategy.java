package com.guorong.dict;

import com.guorong.dict.enums.Dict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过枚举定义，翻译字典值
 */
@Service
public class DictTranslationEnumStrategy implements DictTranslationStrategy {

    private static Map<Class<? extends Enum<? extends Dict>>, Map<String, String>> dictCacheMap = new HashMap<>();

    @Override
    public String translate(Class<? extends Enum<? extends Dict>> dictEnumClass, String code) {
        Assert.notNull(dictEnumClass, "dictEnumClass is not null");
        Assert.notNull(code, "code is not null");
        Map<String, String> code2NameMap = dictCacheMap.get(dictEnumClass);
        if (CollectionUtils.isEmpty(code2NameMap)) {
            code2NameMap = new HashMap<>();
            for (Enum<? extends Dict> enumConstant : dictEnumClass.getEnumConstants()) {
                Dict dict = (Dict) enumConstant;
                code2NameMap.put(dict.getCode(), dict.getName());
            }
            dictCacheMap.put(dictEnumClass, code2NameMap);
        }
        return code2NameMap.get(code);
    }
}
