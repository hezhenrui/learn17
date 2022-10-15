package com.github.learn17.module.consumer.listeners;

import com.google.gson.Gson;
import com.github.learn17.common.entity.GCDemo;
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
        log.info(record.partition() + " " + record.offset() + " " + new Gson().fromJson(record.value(), GCDemo.class));
        acknowledgment.acknowledge();
    }
}
