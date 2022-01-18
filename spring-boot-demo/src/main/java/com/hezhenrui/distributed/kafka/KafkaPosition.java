package com.hezhenrui.distributed.kafka;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author hzr
 * @date 2022-01-18
 */
public class KafkaPosition {

    private String topic;

    private List<PartitionPosition> partitionPositionList;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<PartitionPosition> getPartitionPositionList() {
        return partitionPositionList;
    }

    public void setPartitionPositionList(List<PartitionPosition> partitionPositionList) {
        this.partitionPositionList = partitionPositionList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", KafkaPosition.class.getSimpleName() + "[", "]")
                .add("topic='" + topic + "'")
                .add("partitionPositionList=" + partitionPositionList)
                .toString();
    }
}

class PartitionPosition {
    private Integer partition;

    private Long position;

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PartitionPosition.class.getSimpleName() + "[", "]")
                .add("partition=" + partition)
                .add("position=" + position)
                .toString();
    }
}
