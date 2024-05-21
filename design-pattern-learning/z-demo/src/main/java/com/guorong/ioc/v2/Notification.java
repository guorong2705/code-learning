package com.guorong.ioc.v2;

class Notification {
    private MessageSender messageSender;

    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void changeSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void send(String cellPhone, String message) {
        messageSender.send(cellPhone, message);
    }
}
