package com.hezhenrui.learn17.module.demo.listeners;

import com.hezhenrui.learn17.common.po.test.TblRocketTransactionEx;
import com.hezhenrui.learn17.core.db.mapper.test.TblRocketTransactionExMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Objects;

@Slf4j
@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketMQTemplateTransaction")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RocketTransactionListener implements RocketMQLocalTransactionListener {

    private final TblRocketTransactionExMapper rocketTransactionExMapper;

    /**
     * 执行本地事务
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        MessageHeaders headers = message.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        TblRocketTransactionEx rocketTransactionEx = (TblRocketTransactionEx) arg;
        try {
            rocketTransactionEx.setCommitId(transactionId);
            rocketTransactionExMapper.insertSelective(rocketTransactionEx);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Throwable e) {
            log.error("执行本地事务错误",e);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 消息回查
     * 检查本地事务的状态，要有数据源去给RocketMQ检验
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        MessageHeaders headers = message.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        // 查询本地记录的事务执行日志
        TblRocketTransactionEx rocketmqTransactionLog = rocketTransactionExMapper.selectOne(
                TblRocketTransactionEx.builder()
                        .commitId(transactionId)
                        .build()
        );
        if (Objects.nonNull(rocketmqTransactionLog)) {
            // 确认消息
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            // 重试消息
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}
