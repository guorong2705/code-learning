package com.guorong.factory.method.v2;

class App {
    public static void main(String[] args) {
        // 精灵
        BlacksmithFactory blacksmithFactory = BlacksmithFactoryMaker.getFactory(BlacksmithFactoryMaker.FactoryType.ELF_BLACKSMITH);
        Weapon weapon = blacksmithFactory.manufactureWeapon(Weapon.WeaponType.SPEAR);
        System.out.println(blacksmithFactory + " " + weapon);

        // 兽人
        blacksmithFactory = BlacksmithFactoryMaker.getFactory(BlacksmithFactoryMaker.FactoryType.ORC_BLACKSMITH);
        weapon = blacksmithFactory.manufactureWeapon(Weapon.WeaponType.SPEAR);
        System.out.println(blacksmithFactory + " " + weapon);
    }
}
