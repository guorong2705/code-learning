package com.guorong.rocketmq.consumer;

import com.guorong.rocketmq.constant.ConsumerGroupConstant;
import com.guorong.rocketmq.constant.TopicConstant;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 顺序消息消费者
 */
@Component
@RocketMQMessageListener(
        topic = TopicConstant.TOPIC_ORDERLY,
        consumerGroup = ConsumerGroupConstant.CONSUMER_GROUP_ORDERLY,
        consumeMode = ConsumeMode.ORDERLY
)
public class OrderlyMessageConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        // 处理消息的逻辑
        System.out.println(String.format("Received orderly message: %s", message));
    }
}
