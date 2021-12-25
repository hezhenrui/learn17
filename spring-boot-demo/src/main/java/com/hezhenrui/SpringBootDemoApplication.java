package com.hezhenrui;

//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;


@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    /**
     * redisson配置类
     */
//    @Configuration
//    public static class RedissonConfig {
//        @Bean
//        public RedissonClient redissonClient() {
//            Config config = new Config();
//            config.useClusterServers().setScanInterval(2000)
//                    .addNodeAddress("redis://127.0.0.1:6379", "redis://127.0.0.1:6380")
//                    .addNodeAddress("redis://127.0.0.1:6381", "redis://127.0.0.1:6382")
//                    .addNodeAddress("redis://127.0.0.1:6383", "redis://127.0.0.1:6384");
//            return Redisson.create(config);
//        }
//    }

}
