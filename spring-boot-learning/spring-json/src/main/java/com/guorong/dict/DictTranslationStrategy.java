package com.guorong.dict;

import com.guorong.dict.enums.Dict;

/**
 * 字典翻译策略类
 */
public interface DictTranslationStrategy {

    String translate(Class<? extends Enum<? extends Dict>> dictEnumClass, String code);
}
