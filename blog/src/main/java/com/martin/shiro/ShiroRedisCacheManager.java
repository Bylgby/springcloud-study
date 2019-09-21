package com.martin.shiro;

import com.martin.common.utils.ShiroRedisCache;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <br>
 * created date 2019/8/30 17:16
 *
 * @author maxiaowei
 */
public class ShiroRedisCacheManager extends AbstractCacheManager {

    private RedisTemplate redisTemplate;

    public ShiroRedisCacheManager(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected Cache createCache(String name) throws CacheException {
        return new ShiroRedisCache(redisTemplate, name);
    }
}
