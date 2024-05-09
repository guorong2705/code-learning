package com.guorong.ioc.v2;

class SmsSender implements MessageSender {
    @Override
    public void send(String cellPhone, String message) {
        System.out.println("发送短信 --->>> " + message);
    }
}
