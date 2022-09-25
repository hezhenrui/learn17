package com.hezhenrui.demo.distributed.rocketMq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SpringTransactionProducer {

    @Autowired
    private RocketMQTemplateOther other;

    /**
     * 发送消息
     *
     */
    public void sendMsg(String topic, String msg) {
        Message<String> message = MessageBuilder.withPayload(msg).build();
        this.other.sendMessageInTransaction(topic, message, null);
        log.info("发送成功");
    }
}

