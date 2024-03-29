package com.github.learn17.module.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.learn17.common.po.test1.TblDemo;
import com.github.learn17.core.db.mapper.test1.TblDemoMapper;
import com.github.learn17.core.thread.enums.ThreadPoolEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CuratorLockController {
    
    private final TblDemoMapper tblDemoMapper;

    private final CuratorFramework client;

    /**
     * 锁测试共享变量
     */
    private Integer lockCount = 10000;



    @GetMapping("/lock")
    public void lock() throws Exception {
        CompletableFuture.runAsync(() -> log.info("1"), ThreadPoolEnum.IO.getInstance());

        PageHelper.startPage(1, 3);
        List<TblDemo> rs = tblDemoMapper.selectAll();

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


