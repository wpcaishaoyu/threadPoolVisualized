package com.ziroom.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author wangpeng
 */
@Service
public class ThreadPoolMonitor {
    @Autowired
    private ThreadPoolExecutor executor;

    public void shortTimeWork() {
        executor.execute(() -> {
            try {
                // 5秒
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                //ignore
            }
        });
    }

    public void longTimeWork() {
        executor.execute(() -> {
            try {
                // 500秒
                Thread.sleep(5000 * 100);
            } catch (InterruptedException e) {
                //ignore
            }
        });
    }

    public void clearTaskQueue() {
        executor.getQueue().clear();
    }
}
