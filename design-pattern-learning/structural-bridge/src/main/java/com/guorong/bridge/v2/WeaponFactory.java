package com.guorong.bridge.v2;

/**
 * 创建武器的 简单工厂 实现
 */
class WeaponFactory {

    /**
     * 创建武器
     * @param weaponType 武器类型
     * @param enchantmentType 魔法类型
     * @return
     */
    public static Weapon createWeapon(WeaponType weaponType, EnchantmentType enchantmentType) {
        switch (weaponType) {
            case Sword:
                return new Sword(enchantmentType.getEnchantment());
            case Hammer:
                return new Hammer(enchantmentType.getEnchantment());
            default:
                throw new RuntimeException("不支持该类型创建");
        }
    }
}
