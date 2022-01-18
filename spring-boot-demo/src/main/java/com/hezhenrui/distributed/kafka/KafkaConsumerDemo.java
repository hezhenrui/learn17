package com.hezhenrui.distributed.kafka;

import java.util.*;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class KafkaConsumerDemo {

    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    private static List<KafkaPosition> kafkaPositionList = null;

    @PostConstruct
    public void handler(){
        kafkaPositionList=getPartitionsForTopic();
    }

    private Consumer<Long, String> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new KafkaConsumer<>(props);
    }

    // 获取某个Topic的所有分区以及分区最新的Offset
    public List<KafkaPosition> getPartitionsForTopic() {
        final Consumer<Long, String> consumer = createConsumer();
        Map<String, List<PartitionInfo>> listTopics= consumer.listTopics();
        List<KafkaPosition> kafkaPositionList = new ArrayList<>();
        listTopics.forEach((k,v)->{
            KafkaPosition kafkaPosition = new KafkaPosition();
            kafkaPosition.setTopic(k);
            List<TopicPartition> tp = new ArrayList<>();
            List<PartitionPosition> partitionPositionList = new ArrayList<>();
            v.forEach(partitionInfo -> {
                PartitionPosition partitionPosition = new PartitionPosition();
                partitionPosition.setPartition(partitionInfo.partition());
                tp.add(new TopicPartition(k,partitionInfo.partition()));
                consumer.assign(tp);
                consumer.seekToEnd(tp);
                partitionPosition.setPosition(consumer.position(new TopicPartition(k, partitionInfo.partition())));
                partitionPositionList.add(partitionPosition);
            });
            kafkaPosition.setPartitionPositionList(partitionPositionList);
            kafkaPositionList.add(kafkaPosition);
        });
        return kafkaPositionList;
    }
}