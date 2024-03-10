package com.guorong.state._00_structure;


class App {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());
        context.request();
        // 改变状态(环境和状态相关的行为也发生改变)
        context.changeState(new ConcreteStateB());
        context.request();
    }
}
