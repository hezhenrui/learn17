package com.hezhenrui.distributed.rabbitmq;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author hzr
 * @date 2021-05-18
 */
@Component
public class QueueOrder {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queueName", durable = "true", autoDelete = "true"),
            exchange = @Exchange(value = "testExchange"),
            key = "queue.demo.test01")
    )
    public void queueDemoTest01(@Payload String i) {
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queueName", durable = "true", autoDelete = "true"),
            exchange = @Exchange(value = "testExchange"),
            key = "queue.demo.test02")
    )
    public void queueDemoTest02(@Payload String i) {
    }
}

