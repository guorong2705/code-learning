package com.guorong.factory.method.v1;

class App {
    public static void main(String[] args) {
        // 精灵
        BlacksmithFactory blacksmithFactory = new ElfBlacksmithFactory();
        Weapon weapon = blacksmithFactory.manufactureWeapon(Weapon.WeaponType.SPEAR);
        System.out.println(blacksmithFactory + " " + weapon);
        // 兽人
        blacksmithFactory = new OrcBlacksmithFactory();
        weapon = blacksmithFactory.manufactureWeapon(Weapon.WeaponType.SHORT_SWORD);
        System.out.println(blacksmithFactory + " " + weapon);
    }
}
