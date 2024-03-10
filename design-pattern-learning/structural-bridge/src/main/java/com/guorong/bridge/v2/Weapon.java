package com.guorong.bridge.v2;

/**
 * 武器
 */
abstract class Weapon {

    /**
     * 附加魔法功能
     */
    protected Enchantment enchantment;

    public Weapon(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    /**
     * 准备
     */
    public abstract void prepare();

    /**
     * 攻击
     */
    public abstract void attack();

    /**
     * 停用
     */
    public abstract void stop();

    /**
     * 获取附加魔法
     * @return
     */
    public Enchantment getEnchantment() {
        return enchantment;
    }
}

/**
 * 剑
 */
class Sword extends Weapon {

    public Sword(Enchantment enchantment) {
        super(enchantment);
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

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }
}

/**
 * 锤子
 */
class Hammer extends Weapon {

    public Hammer(Enchantment enchantment) {
        super(enchantment);
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

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }
}
