package com.guorong.alert.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通知类
 */
class Notification {

    public void notify(Level level, String message) {
        String levelName = level.getName();
        if (level == Level.SEVERE) {
            System.out.println("发送短信通知 --->>> " + levelName);
        }
        else if (level == Level.URGENCY) {
            System.out.println("发送邮件通知 --->>> " + levelName);
        }
        else if (level == Level.NORMAL) {
            System.out.println("发送内部通知 --->>> " + levelName);
        }
        else if (level == Level.TRIVIAL) {
            System.out.println("不进行任何通知 --->>> " + levelName);
        }
    }


    @Getter
    @AllArgsConstructor
    enum Level {
        SEVERE("严重"),
        URGENCY("紧急"),
        NORMAL("普通"),
        TRIVIAL("无关紧要"),
        ;
        private final String name;
    }
}
