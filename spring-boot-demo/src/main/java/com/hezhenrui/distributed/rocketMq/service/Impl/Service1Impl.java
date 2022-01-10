package com.hezhenrui.distributed.rocketMq.service.Impl;

import com.hezhenrui.distributed.rocketMq.service.Service1;
import com.hezhenrui.mapper.test.InventoryStatementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hzr
 * @date 2022-01-10
 */
@Service
public class Service1Impl implements Service1 {
    @Autowired
    InventoryStatementMapper inventoryStatementMapper;

    @Override
    public void reduce() {
        inventoryStatementMapper.reduceNumById(1);
    }
}
