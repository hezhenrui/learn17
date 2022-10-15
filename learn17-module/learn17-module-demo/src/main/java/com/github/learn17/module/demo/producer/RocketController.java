package com.github.learn17.module.demo.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rocket")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RocketController {

    private final RocketMQTemplateOther rocketMQTemplateOther;

    @PostMapping("/send")
    public String send() {
        Message<String> message = MessageBuilder.withPayload("用户A账户减500元,用户B账户加500元。").build();
        rocketMQTemplateOther.send("pay_topic", message);
        log.info("发送成功");
        return "发送成功";
    }

    @PostMapping("/sendTransaction")
    public String sendTransaction() {
        Message<String> message = MessageBuilder.withPayload("用户A账户减500元,用户B账户加500元。").build();
        rocketMQTemplateOther.send("pay_topic", message);
        log.info("发送成功");
        return "发送成功";
    }

}


