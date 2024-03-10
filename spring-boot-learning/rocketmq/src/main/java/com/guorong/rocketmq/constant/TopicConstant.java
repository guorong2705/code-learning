package com.guorong.rocketmq.constant;

public class TopicConstant {
    private static final String PREFIX = "rocketmq";
    /**
     * 普通主题
     */
    public static final String TOPIC_COMMON = PREFIX + "_common";

    /**
     * 顺序消息主题
     */
    public static final String TOPIC_ORDERLY = PREFIX + "_orderly";

    /**
     * 带标签的主题
     */
    public static final String TOPIC_TAG = PREFIX + "_tag";
}
