package com.hezhenrui.learn17.module.consumer.listeners;

import com.hezhenrui.learn17.common.po.test.TblRocketTransactionEx;
import com.hezhenrui.learn17.common.po.test1.TblRocketConsumer;
import com.hezhenrui.learn17.core.db.mapper.test1.TblRocketConsumerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = "rocket_transaction")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class RocketTransactionConsumer implements RocketMQListener<TblRocketTransactionEx> {

    private final TblRocketConsumerMapper rocketConsumerMapper;


    @Override
    public void onMessage(TblRocketTransactionEx message) {
        rocketConsumerMapper.insertSelective(TblRocketConsumer.builder().data(message.getLog()).build());
        log.info("rocket_transaction消费完毕{}", message);
    }
}

