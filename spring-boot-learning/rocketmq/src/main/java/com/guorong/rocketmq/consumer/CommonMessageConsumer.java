package com.guorong.rocketmq.consumer;

import com.guorong.rocketmq.constant.ConsumerGroupConstant;
import com.guorong.rocketmq.constant.TopicConstant;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        topic = TopicConstant.TOPIC_COMMON,
        consumerGroup = ConsumerGroupConstant.CONSUMER_GROUP_COMMON
)
public class CommonMessageConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        // 处理消息的逻辑
        System.out.println(String.format("Received common message: %s", message));
    }
}
