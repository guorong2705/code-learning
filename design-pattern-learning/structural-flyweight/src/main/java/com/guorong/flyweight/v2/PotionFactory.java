package com.guorong.flyweight.v2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 管理并创建 flyweight 对象
 */
final class PotionFactory {

    // 享元池
    private final Map<PotionType, Potion> potionMap = new HashMap<>();

    /**
     * 获取魔法药水
     * @param potionType
     * @return
     */
    public Potion getPotion(PotionType potionType) {
        Potion potion = potionMap.get(potionType);
        if (Objects.isNull(potion)) {
            potion = potionType.getInstance();
            potionMap.put(potionType, potion);
        }
        return potion;
    }

}
