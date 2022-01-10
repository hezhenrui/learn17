package com.hezhenrui.distributed.rocketMq.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author hzr
 * @date 2022-01-10
 */
public interface Service2 {

    @Transactional(rollbackFor = Exception.class)
    void exception();
}
