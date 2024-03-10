package com.guorong.prototype.v1;

abstract class Prototype<T> implements Cloneable {
    @Override
    protected T clone() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

// 法师
abstract class Mage extends Prototype<Mage> {

}

// 兽人法师
class OrcMage extends Mage {
    // 武器
    private final String weapon;

    public OrcMage(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return String.format("兽人法师使用 %s 武器进行攻击", weapon);
    }
}

// 精灵法师
class ElfMage extends Mage {
    // 帮助类型
    private final String helpType;

    public ElfMage(String helpType) {
        this.helpType = helpType;
    }

    @Override
    public String toString() {
        return String.format("精灵法师使用 %s 进行帮助", helpType);
    }
}

// 战士
abstract class Warrior extends Prototype<Warrior> {

}

// 兽人战士
class OrcWarrior extends Warrior {
    private final String weapon;

    public OrcWarrior(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return String.format("兽人战士使用 %s 武器进行战斗", weapon);
    }
}

// 精灵战士
class ElfWarrior extends Warrior {
    private final String helpType;

    public ElfWarrior(String helpType) {
        this.helpType = helpType;
    }

    @Override
    public String toString() {
        return String.format("精灵战士使用 %s 进行帮助", helpType);
    }
}