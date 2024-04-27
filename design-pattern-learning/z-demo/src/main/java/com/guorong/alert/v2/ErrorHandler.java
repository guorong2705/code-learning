package com.guorong.alert.v2;

class ErrorHandler extends AlertHandler {

    public ErrorHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }
    @Override
    public void check(ApiInfo apiInfo) {
        if (apiInfo.getErrorCount() > rule.getErrorCount()) {
            notification.notify(Notification.Level.NORMAL, "错误超过了...");
        }
    }
}
