package com.hezhenrui.zk.config;

import lombok.Data;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * zookeeper分布式锁配置
 */
@Data
@Configuration
public class CuratorConfig {

    private final CuratorProperties curatorProperties;

    public CuratorConfig(CuratorProperties curatorProperties) {
        this.curatorProperties = curatorProperties;
    }

    @Bean
    public CuratorFramework curatorFramework() {
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
