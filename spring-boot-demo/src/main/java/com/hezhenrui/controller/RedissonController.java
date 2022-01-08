//package com.hezhenrui.controller;
//
//import cn.hutool.core.convert.Convert;
//import cn.hutool.core.util.IdUtil;
//import com.hezhenrui.mapper.test.InventoryStatementMapper;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.context.WebServerApplicationContext;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author hzr
// * @date 2021-11-30
// */
//@RestController
//@RequestMapping("redisson")
//public class RedissonController {
//
//    @Autowired
//    InventoryStatementMapper inventoryStatementMapper;
//
//    @Autowired
//    RedissonClient redissonClient;
//
//    @Autowired
//    WebServerApplicationContext webServerApplicationContext;
//
//    @RequestMapping("string")
//    public String getString(){
//        return Convert.toStr(webServerApplicationContext.getWebServer().getPort());
//    }
//
//    @RequestMapping("lockData")
//    public void lockData(Integer id) throws InterruptedException {
//        String lock1String = "lock:" + IdUtil.fastSimpleUUID();
//        String lock2String = "lock:" + IdUtil.fastSimpleUUID();
//        String lock3String = "lock:" + IdUtil.fastSimpleUUID();
//        RLock lock1 = redissonClient.getLock(lock1String );
//        RLock lock2 = redissonClient.getLock(lock2String);
//        RLock lock3 = redissonClient.getLock(lock3String);
//
//        RLock redLock = redissonClient.getRedLock(lock1, lock2, lock3);
//
//        redLock.lock(10, TimeUnit.SECONDS);
//        boolean res = redLock.tryLock(100, 10, TimeUnit.SECONDS);
//        if (res) {
//            try {
//                inventoryStatementMapper.reduceNumById(id);
//            } finally {
//                redLock.unlock();
//            }
//        }
//    }
//}
