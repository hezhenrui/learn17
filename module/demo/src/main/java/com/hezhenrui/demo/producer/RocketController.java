package com.hezhenrui.demo.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rocket")
public class RocketController {

    private final RocketMQTemplateOther rocketMQTemplateOther;

    public RocketController(RocketMQTemplateOther rocketMQTemplateOther) {
        this.rocketMQTemplateOther = rocketMQTemplateOther;
    }


    @PostMapping("/send")
    public String send() {
        Message<String> message = MessageBuilder.withPayload("用户A账户减500元,用户B账户加500元。").build();
        rocketMQTemplateOther.send("pay_topic", message);
        log.info("发送成功");
        return "发送成功";
    }

}


