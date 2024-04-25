package com.guorong.alert.version01;

/**
 * 报警
 */
class Alert {

    private final AlertRule alertRule;
    private final Notification notification;

    public Alert(AlertRule alertRule, Notification notification) {
        this.alertRule = alertRule;
        this.notification = notification;
    }

    // 检查接口
    public void check(String api, long requestCount, long errorCount, long durationOfSeconds) {
        // TPS
        long tps = requestCount / durationOfSeconds;
        if (tps > alertRule.getMaxTps()) {
            notification.notify(Notification.Level.SEVERE, "tps预警");
        }
        // 错误数量
        if (errorCount> alertRule.getMaxErrorCount()) {
            notification.notify(Notification.Level.NORMAL, "错误超过");
        }
    }
}
