package com.hezhenrui.distributed.rocketMq;

import com.hezhenrui.distributed.rocketMq.service.Service1;
import com.hezhenrui.distributed.rocketMq.service.Service2;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import java.util.HashMap;
import java.util.Map;

@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketMQTemplateOther")
@Slf4j
public class TransactionListenerOther implements RocketMQLocalTransactionListener {

    @Autowired
    Service1 service1;

    @Autowired
    Service2 service2;


    private static Map<String, RocketMQLocalTransactionState> STATE_MAP = new HashMap<>();

    /**
     *  执行业务逻辑
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        String transId = (String) message.getHeaders().get(RocketMQHeaders.PREFIX+RocketMQHeaders.TRANSACTION_ID);
        try {
            service1.reduce();
            service2.exception();
            STATE_MAP.put(transId, RocketMQLocalTransactionState.COMMIT);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
        }

        STATE_MAP.put(transId, RocketMQLocalTransactionState.ROLLBACK);
        return RocketMQLocalTransactionState.UNKNOWN;

    }

    /**
     * 回查
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String transId = (String) message.getHeaders().get(RocketMQHeaders.PREFIX+RocketMQHeaders.TRANSACTION_ID);
        log.info("回查消息 -> transId ={} , state = {}", transId, STATE_MAP.get(transId));
        return STATE_MAP.get(transId);
    }
}



