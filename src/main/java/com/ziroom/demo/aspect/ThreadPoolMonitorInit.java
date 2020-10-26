package com.ziroom.demo.aspect;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangpeng
 */
@Component
public class ThreadPoolMonitorInit implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Autowired
    private ScheduledExecutorService scheduledExecutor;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        scheduledExecutor.scheduleWithFixedDelay(threadPoolExecutor, 0,5, TimeUnit.SECONDS);
    }

    public Runnable threadPoolExecutor = () -> {
        String[] names = applicationContext.getBeanNamesForType(ThreadPoolExecutor.class);
        if (names.length > 0) {
            for (String name : names) {
                ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) applicationContext.getBean(name);
                Iterable<Tag> tags = Collections.singletonList(Tag.of("thread.pool.name", name));
                Metrics.gauge("thread.pool.core.size", tags, poolExecutor, ThreadPoolExecutor::getCorePoolSize);
                Metrics.gauge("thread.pool.largest.size", tags, poolExecutor, ThreadPoolExecutor::getLargestPoolSize);
                Metrics.gauge("thread.pool.max.size", tags, poolExecutor, ThreadPoolExecutor::getMaximumPoolSize);
                Metrics.gauge("thread.pool.active.size", tags, poolExecutor, ThreadPoolExecutor::getActiveCount);
                Metrics.gauge("thread.pool.thread.count", tags, poolExecutor, ThreadPoolExecutor::getPoolSize);
                // 注意如果阻塞队列使用无界队列这里不能直接取size
                Metrics.gauge("thread.pool.queue.size", tags, poolExecutor, e -> e.getQueue().size());
            }
        }
    };
}
