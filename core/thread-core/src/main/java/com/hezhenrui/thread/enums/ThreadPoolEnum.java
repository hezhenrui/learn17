package com.hezhenrui.thread.enums;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

public enum ThreadPoolEnum {

    CPU(Runtime.getRuntime().availableProcessors() + 1, Runtime.getRuntime().availableProcessors() + 1) {
        @Override
        protected ThreadPoolProperties getThreadPoolProperties() {
            return SpringUtil.getBean(ThreadPoolProperties.class);
        }
    },

    IO(Runtime.getRuntime().availableProcessors() * 2 + 1, Runtime.getRuntime().availableProcessors() * 4 + 1) {
        @Override
        protected ThreadPoolProperties getThreadPoolProperties() {
            return SpringUtil.getBean(ThreadPoolProperties.class);
        }
    };

    private final ThreadPoolExecutor threadPoolExecutor;

    ThreadPoolEnum(int corePoolSize, int maximumPoolSize) {
        ThreadFactory threadFactory = new CustomizableThreadFactory(getThreadPoolProperties().getThreadNamePrefix());
        this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, getThreadPoolProperties().getKeepAliveTime(), TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(getThreadPoolProperties().getCapacity()), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public ThreadPoolExecutor getInstance() {
        return this.threadPoolExecutor;
    }

    protected abstract ThreadPoolProperties getThreadPoolProperties();
}
