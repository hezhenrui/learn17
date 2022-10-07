package com.hezhenrui.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.hezhenrui.common.entity.ResultVo;
import com.hezhenrui.common.utils.ResultUtil;
import com.hezhenrui.demo.domain.test.TblRocketTransactionEx;
import com.hezhenrui.demo.producer.RocketMQTemplateTransaction;
import com.hezhenrui.demo.service.RocketTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RocketTransactionServiceImpl implements RocketTransactionService {

    private final RocketMQTemplateTransaction rocketMQTemplateTransaction;

    @Override
    public ResultVo<TblRocketTransactionEx> sendTransaction(TblRocketTransactionEx tblRocketTransactionEx) {
        String transactionId = IdUtil.simpleUUID();
        rocketMQTemplateTransaction.sendMessageInTransaction("rocket_transaction:one", MessageBuilder.withPayload(tblRocketTransactionEx)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                .build(), tblRocketTransactionEx);
        log.info("rocket_transaction消息发送成功{}", tblRocketTransactionEx);
        return ResultUtil.success(tblRocketTransactionEx);
    }
}
