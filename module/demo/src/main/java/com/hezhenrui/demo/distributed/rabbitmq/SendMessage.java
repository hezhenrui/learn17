package com.hezhenrui.demo.distributed.rabbitmq;

import cn.hutool.core.util.StrUtil;
import com.hezhenrui.demo.demo.GCDemo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("sendMessageOO")
    public void sendMessageOO() {
        GCDemo gcDemo;
        for (int i=0;i<100;i++){
            gcDemo = new GCDemo();
            gcDemo.setId(StrUtil.toString(i));
            amqpTemplate.convertAndSend("amq.direct", "queue.demo.test01", gcDemo);
            amqpTemplate.convertAndSend("amq.direct", "queue.demo.test02", gcDemo);
        }
    }
}
