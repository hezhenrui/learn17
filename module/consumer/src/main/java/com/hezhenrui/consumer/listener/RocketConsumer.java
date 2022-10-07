package com.hezhenrui.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "pay_topic",
        consumerGroup = "${rocketmq.consumer.group}",
        selectorExpression = "*")
public class RocketConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("接收到消息 -> {}", message);
    }
}

