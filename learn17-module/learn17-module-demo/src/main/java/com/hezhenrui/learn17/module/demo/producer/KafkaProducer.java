package com.hezhenrui.learn17.module.demo.producer;

import com.google.gson.Gson;
import com.hezhenrui.learn17.common.entity.GCDemo;
import com.hezhenrui.learn17.common.utils.StringUtil;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hzr
 * @date 2022-01-11
 */
@RestController
@RequestMapping("kafka")
public class KafkaProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("send")
    public void send(){
        for (int i=0;i<100;i++){
            GCDemo gcDemo = new GCDemo();
            gcDemo.setId(StringUtil.toString(i));
            ListenableFuture<SendResult<String, String>> sendResultListenableFuture=kafkaTemplate.send("topic",StringUtil.toString(i), new Gson().toJson(gcDemo));
            sendResultListenableFuture.addCallback(result -> {

            }, ex -> {

            });
        }
    }


}
