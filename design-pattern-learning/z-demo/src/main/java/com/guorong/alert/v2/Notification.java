package com.guorong.alert.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;

class Notification {

    public void notify(Level level, String message) {
        if (level == Level.SEVERE) {
            System.out.println("严重 --->>> " + message);
        } else if (level == Level.NORMAL) {
            System.out.println("普通 --->>> " + message);
        } else if (level == Level.TRIVIAL) {
            System.out.println("无关紧要 --->>> " + message);
        }
    }

    @Getter
    @AllArgsConstructor
    enum Level {
        SEVERE("严重"),
        NORMAL("普通"),
        TRIVIAL("无关紧要"),
        ;
        private String name;
    }
}
