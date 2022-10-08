package com.hezhenrui.learn17.core.thread.enums;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "hezhenrui.thread-core")
public class ThreadPoolProperties {

    private long keepAliveTime;

    private String threadNamePrefix;

    private Integer capacity;

}
