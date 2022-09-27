package com.hezhenrui.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * redisson集群配置
 */
@Component
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useClusterServers().setScanInterval(2000)
                .addNodeAddress("redis://127.0.0.1:6379", "redis://127.0.0.1:6380")
                .addNodeAddress("redis://127.0.0.1:6381", "redis://127.0.0.1:6382")
                .addNodeAddress("redis://127.0.0.1:6383", "redis://127.0.0.1:6384");
        return Redisson.create(config);
    }
}
