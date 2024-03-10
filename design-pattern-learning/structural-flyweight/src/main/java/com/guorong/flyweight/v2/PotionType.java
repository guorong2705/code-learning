package com.guorong.flyweight.v2;

import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

/**
 * 药水类型枚举
 */
@RequiredArgsConstructor
enum PotionType{
    /**康复*/
    HEALING(HealingPotion::new),
    /**圣水*/
    HOLY_WATER(HolyWaterPotion::new),
    /**隐形*/
    INVISIBILITY(InvisibilityPotion::new),
    ;
    private final Supplier< ? extends Potion> supplier;

    public Potion getInstance() {
        return supplier.get();
    }
}
