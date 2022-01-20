package com.hezhenrui.distributed.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hzr
 * @date 2021-05-18
 */
@RestController
@RequestMapping("sendMessage")
public class SendMessage {

    private final AmqpTemplate amqpTemplate;

    public SendMessage(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @RequestMapping("sendMessageOO")
    public void sendMessageOO() {
        for (int i=0;i<100;i++){
            amqpTemplate.convertAndSend("amq.direct", "queue.demo.test01", i);
            amqpTemplate.convertAndSend("amq.direct", "queue.demo.test02", i);
        }
    }
}
