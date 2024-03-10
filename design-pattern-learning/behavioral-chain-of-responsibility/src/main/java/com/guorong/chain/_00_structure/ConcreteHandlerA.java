package com.guorong.chain._00_structure;

class ConcreteHandlerA extends BaseHandler {
    @Override
    protected boolean canHandle(String request) {
        return false;
    }
    @Override
    public void handle(String request) {
        if (canHandle(request)) {
            System.out.println(this.getClass().getSimpleName() + "处理了请求...");
            return;
        }
        super.handle(request);
    }
}