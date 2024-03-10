package com.guorong.prototype.v2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 英雄管理器
 */
class HeroManager {
    // 英雄存储库
    private Map<Class<? extends Prototype>, Prototype> prototypeMap = new HashMap<>();

    // 注册原型
    public void register(Prototype prototype) {
        Optional.ofNullable(prototype)
                .orElseThrow(() -> new NullPointerException("prototype 不能为空"));
        prototypeMap.put(prototype.getClass(), prototype);
    }

    public <T> T create(Class<? extends Prototype<T>> clazz) {
        Prototype<T> prototype = prototypeMap.get(clazz);
        // 判空
        Optional.ofNullable(prototype)
                .orElseThrow(() -> new RuntimeException(String.format("%s 类型原型未注册", clazz.getName())));
        return prototype.clone();
    }

}
