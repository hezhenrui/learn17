package com.hezhenrui.learn17.core.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * redisson集群配置
 */
@Configuration
public class RedissonConfig {

    private final RedisProperties redisProperties;

    public RedissonConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        List<String> nodes = redisProperties.getCluster().getNodes();
        config.useClusterServers().addNodeAddress(nodes.toArray(new String[nodes.size()]));
        return Redisson.create(config);
    }
}
