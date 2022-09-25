package com.hezhenrui.demo.distributed.rocketMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rocket")
public class ProducerController {

    @Autowired
    private SpringTransactionProducer springTransactionProducer;

    @GetMapping("/sendMsg")
    public String sendMsg() {
        springTransactionProducer.sendMsg("pay_topic", "用户A账户减500元,用户B账户加500元。");
        return "发送成功";
    }

}


