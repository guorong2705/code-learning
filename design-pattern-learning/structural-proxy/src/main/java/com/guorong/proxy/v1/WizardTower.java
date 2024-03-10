package com.guorong.proxy.v1;

/**
 * 巫师塔
 */
interface WizardTower {
    void enter(Wizard wizard);
}

/**
 * 象牙塔
 */
class IvoryTower implements WizardTower {

    @Override
    public void enter(Wizard wizard) {
        System.out.println(String.format("%s 进入象牙塔", wizard));
    }
}

/**
 * 巫师塔代理
 */
class WizardTowerProxy implements WizardTower {
    // 允许进入的巫师数
    private final static int MAX_WIZARD_ALLOWED = 3;
    // 当前进入的巫师数量
    private int currentWizardNum = 0;

    private WizardTower wizardTower;

    public WizardTowerProxy(WizardTower wizardTower) {
        this.wizardTower = wizardTower;
    }

    @Override
    public void enter(Wizard wizard) {
        if (currentWizardNum < MAX_WIZARD_ALLOWED) {
            wizardTower.enter(wizard);
            currentWizardNum++;
            return;
        }
        System.out.println(String.format("%s 不允许进入,人数超过限制=", wizard, MAX_WIZARD_ALLOWED));
    }
}
