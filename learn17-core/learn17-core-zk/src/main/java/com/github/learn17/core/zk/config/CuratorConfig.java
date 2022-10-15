package com.github.learn17.core.zk.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * zookeeper分布式锁配置
 */
@Configuration
@EnableConfigurationProperties(CuratorProperties.class)
public class CuratorConfig {

    @Bean
    public CuratorFramework curatorFramework(CuratorProperties curatorProperties) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(curatorProperties.getElapsedTimeMs(), curatorProperties.getElapsedTimeMs());
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                curatorProperties.getConnectString(),
                curatorProperties.getSessionTimeoutMs(),
                curatorProperties.getConnectionTimeoutMs(),
                retryPolicy);
        client.start();
        return client;
    }

}
