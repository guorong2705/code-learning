package com.guorong.factory.method.v1;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/**
 * 铁匠
 */
interface BlacksmithFactory {
    // 构建武器
    Weapon manufactureWeapon(Weapon.WeaponType weaponType);
}

/**
 * 兽人铁匠
 */
class OrcBlacksmithFactory implements BlacksmithFactory {
    private static final Map<Weapon.WeaponType, OrcWeapon> ORC_ARSENAL_MAP;
    static {
        ORC_ARSENAL_MAP = new EnumMap(Weapon.WeaponType.class);
        Arrays.stream(Weapon.WeaponType.values())
                .forEach(type -> ORC_ARSENAL_MAP.put(type, new OrcWeapon(type)));
    }
    @Override
    public Weapon manufactureWeapon(Weapon.WeaponType weaponType) {
        return ORC_ARSENAL_MAP.get(weaponType);
    }
    @Override
    public String toString() {
        return "这是一个兽人铁匠";
    }
}


/**
 * 精灵铁匠
 */
class ElfBlacksmithFactory implements BlacksmithFactory {
    private static final Map<Weapon.WeaponType, Weapon> ELF_ARSENAL_MAP;

    static {
        ELF_ARSENAL_MAP = new EnumMap(Weapon.WeaponType.class);
        Arrays.stream(Weapon.WeaponType.values()).forEach(type -> ELF_ARSENAL_MAP.put(type, new ElfWeapon(type)));
    }

    @Override
    public Weapon manufactureWeapon(Weapon.WeaponType weaponType) {
        return ELF_ARSENAL_MAP.get(weaponType);
    }

    @Override
    public String toString() {
        return "这是一个精灵铁匠";
    }
}