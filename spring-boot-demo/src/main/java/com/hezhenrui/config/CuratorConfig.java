package com.hezhenrui.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CuratorConfig {
    @Value("${spring.curator.retryCount}")
    private int retryCount;
    @Value("${spring.curator.elapsedTimeMs}")
    private int elapsedTimeMs;
    @Value("${spring.curator.connectString}")
    private String connectString;
    @Value("${spring.curator.sessionTimeoutMs}")
    private int sessionTimeoutMs;
    @Value("${spring.curator.connectionTimeoutMs}")
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
