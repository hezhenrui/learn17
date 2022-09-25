package com.hezhenrui.zk.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * zookeeper分布式锁配置
 */
@Component
public class CuratorConfig {
    @Value("${spring.curator.retry-count}")
    private int retryCount;
    @Value("${spring.curator.elapsed-time-ms}")
    private int elapsedTimeMs;
    @Value("${spring.curator.connect-string}")
    private String connectString;
    @Value("${spring.curator.session-timeout-ms}")
    private int sessionTimeoutMs;
    @Value("${spring.curator.connection-timeout-ms}")
    private int connectionTimeoutMs;

    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(elapsedTimeMs, retryCount);
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                connectString,
                sessionTimeoutMs,
                connectionTimeoutMs,
                retryPolicy);
        client.start();
        return client;
    }

}
