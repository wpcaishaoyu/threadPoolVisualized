package com.ziroom.demo.controller;

import com.ziroom.demo.service.ThreadPoolMonitor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpeng
 */
@RestController
@RequiredArgsConstructor
public class ThreadPoolMonitorController {
    private final ThreadPoolMonitor threadPoolMonitor;

    @GetMapping(value = "/shortTimeWork")
    public ResponseEntity<String> shortTimeWork() {
        threadPoolMonitor.shortTimeWork();
        return ResponseEntity.ok("success");
    }

    @GetMapping(value = "/longTimeWork")
    public ResponseEntity<String> longTimeWork() {
        threadPoolMonitor.longTimeWork();
        return ResponseEntity.ok("success");
    }

    @GetMapping(value = "/clearTaskQueue")
    public ResponseEntity<String> clearTaskQueue() {
        threadPoolMonitor.clearTaskQueue();
        return ResponseEntity.ok("success");
    }
}
