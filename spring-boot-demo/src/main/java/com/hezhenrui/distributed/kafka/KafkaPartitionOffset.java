package com.hezhenrui.distributed.kafka;

import java.lang.annotation.*;

/**
 * @author hzr
 * @date 2022-01-18
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KafkaPartitionOffset {
}
