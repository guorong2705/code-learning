package com.guorong.rocketmq.consumer;

import com.guorong.rocketmq.constant.ConsumerGroupConstant;
import com.guorong.rocketmq.constant.TopicConstant;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 过滤：指定tag
 */
@Component
@RocketMQMessageListener(
        topic = TopicConstant.TOPIC_TAG,
        consumerGroup = ConsumerGroupConstant.CONSUMER_GROUP_TAG,
        selectorExpression = "tagA||tagC"
)
public class TagMessageConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        // 处理消息的逻辑
        System.out.println(String.format("Received tag message: %s", message));
    }
}
