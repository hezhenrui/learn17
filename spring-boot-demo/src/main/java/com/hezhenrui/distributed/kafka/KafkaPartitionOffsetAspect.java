package com.hezhenrui.distributed.kafka;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author hzr
 * @date 2022-01-18
 */
@Aspect
@Component
public class KafkaPartitionOffsetAspect {

    private SpelExpressionParser spelParser = new SpelExpressionParser();

    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    private static List<KafkaPosition> kafkaPositionList;

    @PostConstruct
    public void handler() {
        kafkaPositionList = getPartitionsForTopic();
    }

    @Pointcut("@annotation(com.hezhenrui.distributed.kafka.KafkaPartitionOffset)")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint, ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        KafkaListener kafkaListener = method.getDeclaredAnnotation(KafkaListener.class);
        List<org.springframework.kafka.annotation.TopicPartition> topicPartitions = Arrays.asList(kafkaListener.topicPartitions());
        topicPartitions.forEach(topicPartition ->
                kafkaPositionList.stream().filter(kafkaPosition -> Objects.equals(topicPartition.topic(), kafkaPosition.getTopic())).findFirst().ifPresent(kafkaPosition -> {
                    List<PartitionOffset> partitionOffsetList = Arrays.asList(topicPartition.partitionOffsets());
                    partitionOffsetList.forEach(partitionOffset ->
                            kafkaPosition.getPartitionPositionList().stream().filter(partitionPosition ->
                                    Objects.equals(partitionOffset.partition(), Convert.toStr(partitionPosition.getPartition()))).findFirst().ifPresent(partitionPosition -> {
                                        spelParser.parseRaw("initialOffset").setValue(partitionOffset,Convert.toStr(partitionPosition.getPartition() + 1));
                            }));
                }));
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
        Map<String, List<PartitionInfo>> listTopics = consumer.listTopics();
        List<KafkaPosition> kafkaPositionList = new ArrayList<>();
        listTopics.forEach((k, v) -> {
            KafkaPosition kafkaPosition = new KafkaPosition();
            kafkaPosition.setTopic(k);
            List<TopicPartition> tp = new ArrayList<>();
            List<PartitionPosition> partitionPositionList = new ArrayList<>();
            v.forEach(partitionInfo -> {
                PartitionPosition partitionPosition = new PartitionPosition();
                partitionPosition.setPartition(partitionInfo.partition());
                tp.add(new TopicPartition(k, partitionInfo.partition()));
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
