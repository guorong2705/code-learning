package com.guorong.alert.v2;

class TpsHandler extends AlertHandler {

    public TpsHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }
    @Override
    public void check(ApiInfo apiInfo) {
        long tps = apiInfo.getRequestCount() / apiInfo.getDurationOfSeconds();
        if (tps > rule.getMaxTps()) {
            notification.notify(Notification.Level.SEVERE, "tps超过值了...");
        }
    }
}
