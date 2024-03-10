package com.guorong.prototype.v2;

class App {
    public static void main(String[] args) {
        HeroManager manager = new HeroManager();
        // 精灵法师
        ElfMage elfMage = new ElfMage("魔法药水");
        manager.register(elfMage);
        assert elfMage != manager.create(ElfMage.class) : "复制对象和原来对象是同一个对象";
        // 兽人法师
        OrcMage orcMage = new OrcMage("锤子");
        manager.register(orcMage);
        assert orcMage != manager.create(OrcMage.class) : "创建对象和原来是同一个对象";
    }
}
