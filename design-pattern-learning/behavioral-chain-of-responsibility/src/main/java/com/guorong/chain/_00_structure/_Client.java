package com.guorong.chain._00_structure;

class _Client {
    public static void main(String[] args) {
        // 设置处理器链
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        handlerA.setNext(handlerB);
        // 执行处理器
        handlerA.handle("哈哈");
    }
}
