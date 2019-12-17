package com.martin.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 公共配置
 * <br>
 * created date 2019/12/17 15:06
 *
 * @author maxiaowei
 */
@Configuration
public class CommonConfig {


    @Bean(name = "executorService")
    public ExecutorService getThreadPoolExecutor() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory,
                // 拒绝策略, 此处使用CallerRunsPolicy 即让调用线程去执行任务
                new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }

    /**
     * 线程池的配置
     */
    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setKeepAliveSeconds(300);
        taskExecutor.setQueueCapacity(200);
        taskExecutor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setThreadNamePrefix("demo-pool-");
        taskExecutor.setDaemon(false);
        return taskExecutor;
    }
}
