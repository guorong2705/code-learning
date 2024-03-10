package com.guorong.rocketmq.consumer;

import com.guorong.rocketmq.constant.TopicConstant;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProducerTests {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    // 发送普通消息
    @Test
    public void sendCommonMessage() {
        for (int i = 0; i < 10; i++) {
            Message<String> message = MessageBuilder.withPayload(LocalDateTime.now().toString()).build();
            rocketMQTemplate.send(TopicConstant.TOPIC_COMMON, message);
        }
    }

    // 发送顺序消息
    @Test
    public void sendOrderlyMessage() {
        for (int i = 1; i <= 10; i++) {
            Message<String> message = MessageBuilder.withPayload(String.valueOf(i)).build();
            String hashKey = "hashKey";
            rocketMQTemplate.syncSendOrderly(TopicConstant.TOPIC_ORDERLY, message, hashKey);
        }
    }

    @Test
    public void sendTagMessage() {
        Message<String> messageA = MessageBuilder.withPayload("hello-tagA").build();
        Message<String> messageB = MessageBuilder.withPayload("hello-tagB").build();
        Message<String> messageC = MessageBuilder.withPayload("hello-tagC").build();
        // 发送消息
        rocketMQTemplate.convertAndSend(TopicConstant.TOPIC_TAG + ":tagA", messageA);
        rocketMQTemplate.convertAndSend(TopicConstant.TOPIC_TAG + ":tagB", messageB);
        rocketMQTemplate.convertAndSend(TopicConstant.TOPIC_TAG + ":tagC", messageC);
    }

}
