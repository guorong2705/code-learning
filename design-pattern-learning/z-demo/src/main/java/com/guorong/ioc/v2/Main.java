package com.guorong.ioc.v2;

class Main {
    public static void main(String[] args) {
        Notification notification = new Notification(new SmsSender());
        notification.send("18695770658", "你好吗");
        notification.changeSender(new InBoxSender());
        notification.send("18695770658", "你好吗");
    }
}
