package com.hezhenrui.learn17.core.zk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.curator")
public class CuratorProperties {

    private int retryCount;

    private int elapsedTimeMs;

    private String connectString;

    private int sessionTimeoutMs;

    private int connectionTimeoutMs;


}
