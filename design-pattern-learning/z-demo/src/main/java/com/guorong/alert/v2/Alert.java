package com.guorong.alert.v2;

import java.util.ArrayList;
import java.util.List;

class Alert {
    private List<AlertHandler> handlers = new ArrayList<>();

    public void addAlertHandler(AlertHandler handler) {
        handlers.add(handler);
    }

    public void check(ApiInfo apiInfo) {
        handlers.forEach(handler -> handler.check(apiInfo));
    }
}
