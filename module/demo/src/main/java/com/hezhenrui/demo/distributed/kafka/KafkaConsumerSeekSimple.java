package com.hezhenrui.demo.distributed.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumerSeekSimple {

    private static AtomicBoolean running = new AtomicBoolean(true);

    // 设置服务器地址
    private static final String bootstrapServer = "192.168.110.142:9092";

    // 设置主题
    private static final String topic = "topic-demo3";

    // 设置消费者组
    private static final String groupId = "group.demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        // 设置反序列化key参数信息
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 设置反序列化value参数信息
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 设置服务器列表信息，必填参数，该参数和生产者相同，，制定链接kafka集群所需的broker地址清单，可以设置一个或者多个
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);

        // 设置消费者组信息，消费者隶属的消费组，默认为空，如果设置为空，则会抛出异常，这个参数要设置成具有一定业务含义的名称
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        // 制定kafka消费者对应的客户端id，默认为空，如果不设置kafka消费者会自动生成一个非空字符串。
        properties.put("client.id", "consumer.client.id.demo");

        // 设置每次从最早的offset开始消费
        // properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // 将参数设置到消费者参数中
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        // 订阅主题
        consumer.subscribe(Arrays.asList(topic));

        // 获取消费者所分配到的分区
        Set<TopicPartition> assignment = consumer.assignment();
        System.err.println("打印消费者获取到的分区： " + assignment.toString());

        // timeout参数设置多少合适？太短会使分区分配失败，太长有可能造成一些不必要的等待
        // 获取到指定主题的消息
        consumer.poll(Duration.ofMillis(2000));

//        for (TopicPartition topicPartition : assignment) {
//            // 参数partition表示分区，offset表示指定从分区的那个位置开始消费
//            // 方式一，可以指定位置进行消费
//            consumer.seek(topicPartition, 3);
//        }

        // 指定从分区末尾开始消费，方式二，可以从末端开始倒叙消费
        Map<TopicPartition, Long> endOffsets = consumer.endOffsets(assignment);
        for (TopicPartition topicPartition : assignment) {
            System.err.println("打印消费者获取到offset ： " + ( endOffsets.get(topicPartition) + 1 ));
            consumer.seek(topicPartition, endOffsets.get(topicPartition) + 1);
        }

        try {
            while (running.get()) {
                // 每隔一秒监听一次，拉去指定主题分区的消息
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                if (records.isEmpty()) {
                    break;
                }
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("我要开始消费了： " + record.toString());
                }

                consumer.commitSync();

                // 异步回调，适合消息量非常大，但是允许消息重复的
                consumer.commitAsync(new OffsetCommitCallback() {

                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        if (exception == null) {
                            System.out.println("异步回调成功了,offset : " + offsets);
                        } else {
                            System.err.println("fail to commit offsets " + offsets + " , " + exception);
                        }

                    }
                });

            }
        } finally {
            // 关闭客户端
            consumer.close();
        }

    }
}
