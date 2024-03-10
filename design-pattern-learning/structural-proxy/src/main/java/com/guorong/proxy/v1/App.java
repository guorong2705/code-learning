package com.guorong.proxy.v1;

/**
 * 想象一下当地巫师去学习咒语的一座塔。象牙塔只能通过代理访问，
 * 这确保只有前三个巫师才能进入。这里代理代表塔的功能并为其添加访问控制。
 */
class App {
    public static void main(String[] args) {
        WizardTower proxy = new WizardTowerProxy(new IvoryTower());
        for (int i = 0; i < 6; i++) {
            proxy.enter(new Wizard(String.format("巫师-", i)));
        }
    }
}
