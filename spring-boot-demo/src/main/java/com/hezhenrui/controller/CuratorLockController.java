package com.hezhenrui.controller;

import com.hezhenrui.domain.InventoryStatement;
import com.hezhenrui.mapper.InventoryStatementMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class CuratorLockController {


    private final CuratorFramework client;

    private final InventoryStatementMapper inventoryStatementMapper;

    public CuratorLockController(CuratorFramework client, InventoryStatementMapper inventoryStatementMapper) {
        this.client = client;
        this.inventoryStatementMapper = inventoryStatementMapper;
    }

    /**
     * 锁测试共享变量
     */
    private Integer lockCount = 10000;



    @GetMapping("/lock")
    public void lock() throws Exception {
        // Zk分布式锁
        InterProcessMutex lock = new InterProcessMutex(client, "/lock/lockCount");
        if (lock.acquire(5, TimeUnit.SECONDS)) {
            try {
                InventoryStatement inventoryStatementBegin =inventoryStatementMapper.selectByPrimaryKey(1);
                if (inventoryStatementBegin.getNum()<=0){
                    log.error("已经卖完");
                    return;
                }
                inventoryStatementMapper.reduceNumById(1);
                InventoryStatement inventoryStatementEnd =inventoryStatementMapper.selectByPrimaryKey(1);
                log.info("开始数量:"+inventoryStatementBegin.getNum()+"结束数量:"+inventoryStatementEnd.getNum());
            } finally {
                lock.release();
            }
        }
    }

    @GetMapping("/lock1")
    public void lock1() throws Exception {
        InventoryStatement inventoryStatementBegin =inventoryStatementMapper.selectByPrimaryKey(1);
        if (inventoryStatementBegin.getNum()<=0){
            log.error("已经卖完");
            return;
        }
        inventoryStatementMapper.reduceNumById(1);
        InventoryStatement inventoryStatementEnd =inventoryStatementMapper.selectByPrimaryKey(1);
        log.info("开始数量:"+inventoryStatementBegin.getNum()+"结束数量:"+inventoryStatementEnd.getNum());
    }
}

