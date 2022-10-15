package com.github.learn17.core.thread.enums;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "learn17.thread-core")
public class ThreadPoolProperties {

    private long keepAliveTime;

    private String threadNamePrefix;

    private Integer capacity;

}
