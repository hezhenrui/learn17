package com.hezhenrui.distributed.kafka;

import com.alibaba.fastjson.JSON;
import com.hezhenrui.demo.GCDemo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author hzr
 * @date 2022-01-17
 */
@Component
public class KafkaListenerDemo {

    @KafkaListener(topics = "topic",
            topicPartitions = @TopicPartition(
                    topic = "topic", partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0"),
                    @PartitionOffset(partition = "1", initialOffset = "0"), @PartitionOffset(partition = "2", initialOffset = "0")}),
            groupId = "${spring.kafka.consumer.group-id}")
    public void sendMessage(ConsumerRecord<String, String> record,Acknowledgment acknowledgment) {
        System.out.print(record.partition() + " "+record.offset()+" ");
        System.out.println(JSON.parseObject(record.value(), GCDemo.class));
        acknowledgment.acknowledge();

    }
}
