package com.github.learn17.core.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * redisson集群配置
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(RedisProperties redisProperties) {
        Config config = new Config();
        String url = "redis://"+redisProperties.getHost()+":"+redisProperties.getPort();
        config.useSingleServer().setAddress(url);
        return Redisson.create(config);
    }
}
