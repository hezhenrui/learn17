package com.hezhenrui.demo.distributed.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author hzr
 * @date 2021-05-18
 */
@Component
public class QueueOrder {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "demo01", durable = "true", autoDelete = "true"),
            exchange = @Exchange(value = "amq.direct"),
            key = "queue.demo.test01")
    )
    public void queueDemoTest01(@Payload String i) {
        System.out.println("queue.demo.test01" + i);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "demo01", durable = "true", autoDelete = "true"),
            exchange = @Exchange(value = "amq.direct"),
            key = "queue.demo.test02")
    )
    public void queueDemoTest02(Message message, Channel channel) throws IOException {
        // 采用手动应答模式, 手动确认应答更为安全稳定
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("txMessageListener tag:" + deliveryTag + ",message:" + new String(message.getBody()));
        // 第一个参数是消息标识, 第二个是批量确认; false当前消息确认, true此次之前的消息确认
        channel.basicAck(deliveryTag, false);

        // 消费失败，消息重返队列
        channel.basicNack(deliveryTag,false,true);
        System.out.println("queue.demo.test02");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "demo02", durable = "true", autoDelete = "true"),
            exchange = @Exchange(value = "amq.direct"),
            key = "queue.demo.test02")
    )
    public void queueDemoTest03(@Payload String i) {
        System.out.println("queue.demo.test03" + i);
    }
}

