package com.guorong.decorator.v1;

// 拥有棍子的巨魔(装饰器：添加额外功能)
class ClubbedTroll implements Troll {

    // 被装饰者
    private Troll decorated;

    public ClubbedTroll(Troll decorated) {
        this.decorated = decorated;
    }

    @Override
    public void attack() {
        decorated.attack();
        System.out.println("巨魔用棍子向你挥舞 ...");
    }

    @Override
    public int getAttachPower() {
        return 12 + decorated.getAttachPower();
    }
}
