package com.hezhenrui.demo.distributed.kafka;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author hzr
 * @date 2022-01-17
 */
@Component
public class KafkaListenerDemo {

//    @KafkaListener(topics = "topic", groupId = "${spring.kafka.consumer.group-id}",containerFactory = "myKafkaContainerFactory")
//    public void sendMessage(ConsumerRecord<String, String> record,Acknowledgment acknowledgment) {
//        System.out.print(record.partition() + " "+record.offset()+" ");
//        System.out.println(JSON.parseObject(record.value(), GCDemo.class));
//        acknowledgment.acknowledge();
//
//    }
}
