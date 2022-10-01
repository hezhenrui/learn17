package com.hezhenrui.demo.controller;

import com.hezhenrui.thread.enums.ThreadPoolEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class CuratorLockController {


    private final CuratorFramework client;

    public CuratorLockController(CuratorFramework client) {
        this.client = client;
    }

    /**
     * 锁测试共享变量
     */
    private Integer lockCount = 10000;



    @GetMapping("/lock")
    public void lock() throws Exception {
        CompletableFuture.runAsync(()-> log.info("1"), ThreadPoolEnum.IO.getInstance());

        // Zk分布式锁
        InterProcessMutex lock = new InterProcessMutex(client, "/lock/lockCount");

        if (lock.acquire(5, TimeUnit.SECONDS)) {
            try {
                if (lockCount <= 0) {
                    throw new Exception("lockCount不能小于0");
                }
                // 业务逻辑
                lockCount--;
                log.info("lockCount值：" + lockCount);
            } finally {
                lock.release();
            }
        }
    }

    @GetMapping("/lock1")
    public void lock1() throws Exception {
        // 业务逻辑
        lockCount--;
        log.info("lockCount值：" + lockCount);
    }
}


