package com.guorong.flyweight.v1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 管理并创建 flyweight 对象
 */
final class PotionFactory {

    // 享元池
    private final Map<PotionType, Potion> potionMap = new HashMap<>();

    public Potion getPotion(PotionType potionType) {
        Potion potion = potionMap.get(potionType);
        if (Objects.isNull(potion)) {
            switch (potionType) {
                case HEALING:
                    potion = new HealingPotion();
                    potionMap.put(PotionType.HEALING, potion);
                    break;
                case INVISIBILITY:
                    potion = new InvisibilityPotion();
                    potionMap.put(PotionType.INVISIBILITY, potion);
                    break;
                case HOLY_WATER:
                    potion = new HolyWaterPotion();
                    potionMap.put(PotionType.HOLY_WATER, potion);
                    break;
            }
        }
        return potion;
    }

}
