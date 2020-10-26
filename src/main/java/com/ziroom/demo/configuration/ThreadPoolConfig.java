package com.ziroom.demo.configuration;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author wangpeng
 */
@Configuration
public class ThreadPoolConfig {
    @Bean(name = "threadPoolExecutor")
    public ThreadPoolExecutor jobThreadPoolExecutor() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("thread-pool-%d").build();
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(20, namedThreadFactory);
        poolExecutor.setMaximumPoolSize(100);
        return poolExecutor;
    }

    @ConditionalOnMissingBean
    @Bean(name = "scheduleThreadPoolExecutor")
    public ScheduledExecutorService scheduleThreadPoolExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}