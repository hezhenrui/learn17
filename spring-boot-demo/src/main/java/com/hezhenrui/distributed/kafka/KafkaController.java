package com.hezhenrui.distributed.kafka;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.hezhenrui.demo.GCDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hzr
 * @date 2022-01-11
 */
@RestController
@RequestMapping("kafkaController")
public class KafkaController {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("sendMessage")
    public void sendMessage(){
        for (int i=0;i<100;i++){
            GCDemo gcDemo = new GCDemo();
            gcDemo.setId(Convert.toStr(i));
            ListenableFuture<SendResult<String, String>> sendResultListenableFuture=kafkaTemplate.send("topic",Convert.toStr(i), JSON.toJSONString(gcDemo));
            sendResultListenableFuture.addCallback(result -> {

            }, ex -> {

            });
        }
    }


}
