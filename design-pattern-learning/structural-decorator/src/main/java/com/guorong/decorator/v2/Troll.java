package com.guorong.decorator.v2;

/**
 * 巨魔
 */
interface Troll {
    // 攻击
    void attack();
    // 获取攻击力
    int getAttachPower();
}

/**
 * 简单巨魔
 */
class SimpleTroll implements Troll {

    @Override
    public void attack() {
        System.out.println("巨魔试图抓住你 ...");
    }

    @Override
    public int getAttachPower() {
        return 10;
    }
}

