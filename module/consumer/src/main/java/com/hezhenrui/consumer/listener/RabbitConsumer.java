package com.hezhenrui.consumer.listener;

import com.hezhenrui.common.annotation.RabbitManual;
import com.hezhenrui.common.entity.GCDemo;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author hzr
 * @date 2021-05-18
 */
@Slf4j
@Component
public class RabbitConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "demo01", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "amq.direct"),
            key = "queue.demo.test01")
    )
    public void queueDemoTest01(@Payload GCDemo gcDemo) {
        System.out.println("queue.demo.test01" + gcDemo.toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "demo01", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "amq.direct"),
            key = "queue.demo.test02")
    )
    @RabbitManual
    public void queueDemoTest02(@Payload GCDemo gcDemo, Channel channel, Message message) {
        System.out.println("queue.demo.test02" + gcDemo.toString());
    }
}

