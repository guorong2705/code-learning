package com.guorong.rocketmq.constant;

/**
 * 消费者组常量
 */
public class ConsumerGroupConstant {

    private static final String PREFIX = "consumer_group";

    /**
     * 普通消费者组
     */
    public static final String CONSUMER_GROUP_COMMON = PREFIX + "_common";
    /**
     * 顺序消费者组
     */
    public static final String CONSUMER_GROUP_ORDERLY = PREFIX + "_orderly";

    /**
     * 顺序消费者组
     */
    public static final String CONSUMER_GROUP_TAG = PREFIX + "_tag";

}
