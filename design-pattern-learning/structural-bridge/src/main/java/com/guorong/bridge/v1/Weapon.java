package com.guorong.bridge.v1;

/**
 * 武器
 */
interface Weapon {
    /**
     * 准备
     */
    void prepare();
    /**
     * 攻击
     */
    void attack();

    /**
     * 停用
     */
    void stop();
}

/**
 * 剑
 */
class Sword implements Weapon {

    /**
     * 附加魔法功能
     */
    private final Enchantment enchantment;

    public Sword(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void prepare() {
        System.out.println("剑准备中............");
        enchantment.onActivate();
    }

    @Override
    public void attack() {
        System.out.println("剑攻击中............");
        enchantment.apply();
    }

    @Override
    public void stop() {
        System.out.println("剑停止攻击...........");
        enchantment.onDeactivate();
    }
}

/**
 * 锤子
 */
class Hammer implements Weapon {

    /**
     * 附加魔法功能
     */
    private final Enchantment enchantment;

    public Hammer(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void prepare() {
        System.out.println("锤子准备中...........");
        enchantment.onActivate();
    }

    @Override
    public void attack() {
        System.out.println("锤子攻击中...........");
        enchantment.apply();
    }

    @Override
    public void stop() {
        System.out.println("锤子停止攻击..........");
        enchantment.onDeactivate();
    }
}
