package com.martin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * redis 配置对象
 * <br>
 * created date 2019/8/26 14:38
 *
 * @author maxiaowei
 */
@Component
@Data
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisProperties {

    private String nodes;

    private Integer commandTimeout;

    private Integer maxAttempts;

    private Integer maxRedirects;

    private Integer maxActive;

    private Integer maxWait;

    private Integer maxIdle;

    private Integer minIdle;

    private boolean testOnBorrow;

    private String password;
}
