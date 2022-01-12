package com.hezhenrui.distributed.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hzr
 * @date 2022-01-11
 */
@RestController
public class KafkaController {

    @Autowired
    KafkaTemplate kafkaTemplate;


}
