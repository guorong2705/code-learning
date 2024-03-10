package com.guorong.bridge.v1;

/**
 * 客户端程序
 */
class App {
    public static void main(String[] args) {
        // 飞行魔法
        FlyingEnchantment flyingEnchantment = new FlyingEnchantment();
        // 噬魂附魔魔法
        SoulEatingEnchantment soulEatingEnchantment = new SoulEatingEnchantment();
        // 剑
        Sword sword = new Sword(flyingEnchantment);
        goAttack(sword);;
        // 锤子
        Hammer hammer = new Hammer(soulEatingEnchantment);
        goAttack(hammer);
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
