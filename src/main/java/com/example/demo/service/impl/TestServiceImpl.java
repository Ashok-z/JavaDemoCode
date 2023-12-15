package com.example.demo.service.impl;

import com.example.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author 张旭康
 * @Date 2022/12/13
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Override
    public void testAsync(String name) {
        System.out.println("TestService -> " + name);
    }

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Override
    public String asyncMethod() {
        try {
            Thread.sleep(3000);
            log.info("async...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "321";
    }

    @Async("threadPoolTaskExecutor")
    @Override
    public void handleTask() {
        System.out.println(Thread.currentThread().getName() + "正在执行");
    }

    @Async("threadPoolTaskExecutor")
    @Override
    public CompletableFuture<List<String>> handleTaskWithReturnValue(int i) {
        System.out.println(Thread.currentThread().getName() + "正在执行" + i);
        List<String> list = new CopyOnWriteArrayList<>();
        if (i % 3 == 0) {
            list.add(String.valueOf(i));
        }
        return CompletableFuture.completedFuture(list);
    }

    @Override
    public Map<String, Object> test05(String aa) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10000; i++) {
            CompletableFuture<String> s = processElement(aa);
            try {
                String s1 = s.get();
                map.put(i + "", s1);
            } catch (Exception e) {
                map.put(i + "", e.getMessage());
            }
        }
        return map;
    }

    @Async("threadPoolTaskExecutor")
    protected CompletableFuture<String> processElement(String aa) {
        // 调用耗时操作
        System.out.println(Thread.currentThread().getName());
        String s = suJiUserSign(aa);
        return CompletableFuture.completedFuture(s);
    }


    protected String suJiUserSign(String aa) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return aa + aa;
    }


    public void task() {
        executor.submit(() -> {
            try {
                for (int i = 0; i < 10000; i++) {
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName() + "正在执行" + i);
                }
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        });
    }

}
