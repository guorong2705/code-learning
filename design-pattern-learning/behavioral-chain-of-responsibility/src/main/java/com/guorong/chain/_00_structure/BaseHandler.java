package com.guorong.chain._00_structure;

abstract class BaseHandler implements Handler{

    /**
     * 下一个处理器
     */
    private Handler nextHandler;

    /**
     * 是否可以处理请求
     * @param request
     * @return
     */
    protected boolean canHandle(String request) {
        return true;
    }

    @Override
    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handle(String request) {
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}

