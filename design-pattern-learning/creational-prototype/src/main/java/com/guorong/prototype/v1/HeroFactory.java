package com.guorong.prototype.v1;

// 英雄工厂
interface HeroFactory {
    /**
     * 创建法师
     */
    Mage createMage();

    /**
     * 创建战士
     */
    Warrior createWarrior();
}

// 精灵工厂
class ElfFactoryImp implements HeroFactory {
    // 精灵法师
    private final ElfMage mage;
    // 精灵战士
    private final ElfWarrior warrior;

    public ElfFactoryImp(ElfMage mage, ElfWarrior warrior) {
        this.mage = mage;
        this.warrior = warrior;
    }

    @Override
    public Mage createMage() {
        return mage.clone();
    }

    @Override
    public Warrior createWarrior() {
        return warrior.clone();
    }
}


// 兽人工厂
class OrcFactoryImp implements HeroFactory {
    // 兽人法师
    private final OrcMage mage;
    // 兽人战士
    private final OrcWarrior warrior;

    public OrcFactoryImp(OrcMage mage, OrcWarrior warrior) {
        this.mage = mage;
        this.warrior = warrior;
    }

    @Override
    public Mage createMage() {
        return mage.clone();
    }

    @Override
    public Warrior createWarrior() {
        return warrior.clone();
    }
}
