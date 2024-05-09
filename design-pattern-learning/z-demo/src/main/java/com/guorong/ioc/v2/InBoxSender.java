package com.guorong.ioc.v2;

class InBoxSender implements MessageSender {
    @Override
    public void send(String cellPhone, String message) {
        System.out.println("发送站内消息 --->>> " + message);
    }
}
