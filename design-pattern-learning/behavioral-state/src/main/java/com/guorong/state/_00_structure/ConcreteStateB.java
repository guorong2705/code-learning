package com.guorong.state._00_structure;

// 具体状态
class ConcreteStateB extends State{
    @Override
    public void handle() {
        System.out.println(this.getClass().getSimpleName() + "...handle()");
    }
}
