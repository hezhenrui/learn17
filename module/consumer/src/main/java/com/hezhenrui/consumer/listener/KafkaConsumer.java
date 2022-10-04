package com.hezhenrui.consumer.listener;

import com.alibaba.fastjson.JSON;
import com.hezhenrui.common.entity.GCDemo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author hzr
 * @date 2022-01-17
 */
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "topic", groupId = "${spring.kafka.consumer.group-id}")
    public void sendMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        log.info(record.partition() + " " + record.offset() + " " + JSON.parseObject(record.value(), GCDemo.class));
        acknowledgment.acknowledge();
    }
}
