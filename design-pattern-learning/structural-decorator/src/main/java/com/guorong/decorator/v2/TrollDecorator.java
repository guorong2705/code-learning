package com.guorong.decorator.v2;

// 巨魔装饰器(添加功能的基类)
abstract class TrollDecorator implements Troll{
    protected Troll decorated;
    public TrollDecorator(Troll decorated) {
        this.decorated = decorated;
    }
}

// 拥有 棍子 的巨魔(装饰器：添加额外功能)
class ClubbedDecorator extends TrollDecorator {

    public ClubbedDecorator(Troll decorated) {
        super(decorated);
    }

    @Override
    public void attack() {
        decorated.attack();
        System.out.println("巨魔用 棍子 向你挥舞 ...");
    }

    @Override
    public int getAttachPower() {
        return 12 + decorated.getAttachPower();
    }
}
// 拥有 锤子 的巨魔(装饰器：添加额外功能)
class HammerDecorator extends TrollDecorator {

    public HammerDecorator(Troll decorated) {
        super(decorated);
    }

    @Override
    public void attack() {
        decorated.attack();
        System.out.println("巨魔用 锤子 向你挥舞 ...");
    }

    @Override
    public int getAttachPower() {
        return 30 + decorated.getAttachPower();
    }
}