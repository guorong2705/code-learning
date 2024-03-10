package com.guorong.bridge.v2;

import lombok.AllArgsConstructor;

import java.util.function.Supplier;

/**
 * 魔法
 */
interface Enchantment {
    /**
     * 激活魔法
     */
    void onActivate();

    /**
     * 应用魔法
     */
    void apply();

    /**
     * 停用魔法
     */
    void onDeactivate();
}

// 使用单例模式实现
@AllArgsConstructor
enum EnchantmentType {
    // 飞行魔法
    Flying(FlyingEnchantment::new),
    // 噬魂附魔魔法
    SoulEating(SoulEatingEnchantment::new),
    ;
    private final Supplier<Enchantment> supplier;

    // 获取魔法实例
    public Enchantment getEnchantment() {
        return supplier.get();
    }
}

/**
 *  飞行魔法
 */
class FlyingEnchantment implements Enchantment {
    @Override
    public void onActivate() {
        System.out.println("飞行魔法激活............");
    }
    @Override
    public void apply() {
        System.out.println("飞行魔法应用中...........");
    }
    @Override
    public void onDeactivate() {
        System.out.println("飞行魔法停用.............");
    }
}

/**
 * 噬魂附魔魔法
 */
class SoulEatingEnchantment implements Enchantment {

    @Override
    public void onActivate() {
        System.out.println("噬魂附魔魔法激活...........");
    }
    @Override
    public void apply() {
        System.out.println("噬魂附魔魔法应用...........");
    }
    @Override
    public void onDeactivate() {
        System.out.println("噬魂附魔魔法停用............");
    }
}