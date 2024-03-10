package com.guorong.prototype.v1;

/**
 * 原型设计模式 + 抽象工厂模式 实现
 */
class App {
    public static void main(String[] args) {
        // 精灵工厂
        HeroFactory factory = new ElfFactoryImp(
                new ElfMage("能量药水"),
                new ElfWarrior("长剑"));
        System.out.println(factory.createMage());
        System.out.println(factory.createWarrior());

        System.out.println("=========================");

        // 兽人工厂
        factory = new OrcFactoryImp(
                new OrcMage("能量药水"),
                new OrcWarrior("长剑"));
        System.out.println(factory.createMage());
        System.out.println(factory.createWarrior());
    }
}
