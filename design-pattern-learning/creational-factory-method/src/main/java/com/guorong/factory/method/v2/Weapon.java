package com.guorong.factory.method.v2;

/**
 * 武器
 */
interface Weapon {
    /**
     * 武器类型
     */
    enum WeaponType {
        AXE("斧头"),
        SHORT_SWORD("短剑"),
        SPEAR("矛"),
        UNDEFINED("不明确"),
        ;
        private String title;

        WeaponType(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    // 获取武器
    WeaponType getWeaponType();
}

/**
 * 兽人武器
 */
class OrcWeapon implements Weapon {
    private final WeaponType weaponType;

    public OrcWeapon(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public WeaponType getWeaponType() {
        return weaponType;
    }

    @Override
    public String toString() {
        return "兽人武器: " + weaponType;
    }
}

/**
 * 精灵武器
 */
class ElfWeapon implements Weapon {
    private final WeaponType weaponType;

    public ElfWeapon(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public WeaponType getWeaponType() {
        return weaponType;
    }

    @Override
    public String toString() {
        return "精灵武器: " + weaponType;
    }
}
