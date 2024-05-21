package com.guorong.alert.v2;

public class ApplicationContext {

    private Alert alert;

    public void init() {
        AlertRule rule = new AlertRule(10, 10);
        Notification notification = new Notification();
        alert= new Alert();
        alert.addAlertHandler(new TpsHandler(rule, notification));
        alert.addAlertHandler(new ErrorHandler(rule, notification));
    }

    public Alert getAlert() {
        return alert;
    }

    private static final ApplicationContext instance = new ApplicationContext();

    private ApplicationContext() {
        init();
    }

    public static ApplicationContext getInstance() {
        return instance;
    }
}
