package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {

    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 10;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 20;
    /**
     * 队列长度
     */
    private static final int QUEUE_CAPACITY = 100;
    /**
     * 线程池维护线程所允许的空闲时间
     */
    private static final int KEEP_ALIVE_SECONDS = 60;

    @Bean(name = "threadPoolTaskExecutor")	//ThreadPoolTaskExecutor不会自动创建ThreadPoolExecutor，需要手动调initialize才会创建。如果@Bean就不需手动，会自动InitializingBean的afterPropertiesSet来调initialize
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置线程池最大线程数
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        // 线程池活跃的线程数
        executor.setCorePoolSize(CORE_POOL_SIZE);
        // 设置线程队列最大线程数
        executor.setQueueCapacity(QUEUE_CAPACITY);
        // 线程池维护线程所允许的空闲时间
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        executor.setThreadNamePrefix("task-async");//线程前缀名称
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}
