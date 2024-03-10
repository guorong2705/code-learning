package com.guorong.chain._00_structure;

// 处理器接口
interface Handler {
    /**
     * 设置以下一个处理器
     * @param handler
     */
    void setNext(Handler handler);

    /**
     * 处理请求
     * @param request
     */
    void handle(String request);
}
