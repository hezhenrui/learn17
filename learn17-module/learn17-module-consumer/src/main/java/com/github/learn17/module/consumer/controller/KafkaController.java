package com.github.learn17.module.consumer.controller;

import com.github.learn17.api.demo.KafkaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaController {

    private final KafkaClient kafkaClient;

    @PostMapping("send")
    public void send(){
        kafkaClient.send();
    }

}
