package com.guorong.dict;

import com.guorong.util.SpringContextUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DictTranslationStrategyFactory {

    @Value("${dict.translate-typ:enums}")
    private String dictTranslationStrategy;


    @PostConstruct
    private void checkStrategyConfig() {
        log.info("dict.translate-typ 指定值：{} --->>>", dictTranslationStrategy);
        Set<String> nameSet = Arrays.stream(StrategyType.values())
                .map(StrategyType::name)
                .collect(Collectors.toSet());
        if (!nameSet.contains(dictTranslationStrategy)) {
            throw new IllegalArgumentException(String.format("dict.translate-typ 配置错误：取值范围 %s", nameSet));
        }
    }

    public  DictTranslationStrategy instanceDictStrategy() {
        StrategyType strategyType = Enum.valueOf(StrategyType.class, dictTranslationStrategy);
        DictTranslationStrategy bean = SpringContextUtils.getBean(strategyType.getStrategyClazz());
        log.info("获取DictTranslationStrategy实例: {} --->>>", bean.getClass().getName());
        return bean;
    }

    @Getter
    @AllArgsConstructor
    public enum StrategyType {
        // 根据枚举类翻译
        enums(DictTranslationEnumStrategy.class),
        // 从内存中取值翻译
        memory(DictTranslationMemoryStrategy.class),
        ;
        private Class<? extends DictTranslationStrategy> strategyClazz;
    }
}
