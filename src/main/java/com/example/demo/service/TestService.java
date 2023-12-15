package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Author 张旭康
 * @Date 2022/12/13
 */
public interface TestService {

    void testAsync(String name);

    String asyncMethod();

    void handleTask();

    CompletableFuture<List<String>> handleTaskWithReturnValue(int i);

    Map<String, Object> test05(String aa);
}
