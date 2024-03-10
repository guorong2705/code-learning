package com.guorong.bridge.v2;

/**
 * 通过桥接模式，抽象部分（武器:Weapon）和实现部分（魔法:Enchantment）可以独立变化，
 * 从而实现更高的灵活性和可扩展性。这在需要在多个维度上进行扩展的情况下非常有用。
 */
class App {
    public static void main(String[] args) {
        // 剑(飞行魔法)
        Weapon weapon = WeaponFactory.createWeapon(WeaponType.Sword, EnchantmentType.Flying);
        goAttack(weapon);;
        // 锤子(噬魂附魔魔法)
        weapon = WeaponFactory.createWeapon(WeaponType.Hammer, EnchantmentType.SoulEating);
        goAttack(weapon);
    }

    // 进行一次攻击
    private static void goAttack(Weapon weapon) {
        System.out.println("开始攻击 ======================");
        weapon.prepare();
        weapon.attack();
        weapon.stop();
        System.out.println("攻击结束 ======================");
    }
}
