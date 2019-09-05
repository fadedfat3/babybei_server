package com.zhumingbei.babybei_server.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author fadedfate
 * @date Created at 2019/9/5 14:09
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, Object value, Long ttl) {
        redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.MILLISECONDS);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }

    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public Boolean expire(String key, Long ttl) {
        return redisTemplate.expire(key, ttl, TimeUnit.MILLISECONDS);
    }
}
