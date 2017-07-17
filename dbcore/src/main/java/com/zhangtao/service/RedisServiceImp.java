package com.zhangtao.service;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangtao on 2017/7/16.
 */
@Service
public class RedisServiceImp<K> implements RedisService<K, String> {
    private static Log logger = LogFactory.getLog(RedisServiceImp.class);

    @Autowired
    @Qualifier("authRedisTemplate")
    private StringRedisTemplate authRedis;

    @Autowired
    @Qualifier("ctrlRedisTemplate")
    private StringRedisTemplate ctrlRedis;

    @Override
    public boolean authhas(K key) {
        return authRedis.hasKey(key.toString());
    }

    @Override
    public boolean ctrlhas(K key) {
        return ctrlRedis.hasKey(key.toString());
    }

    @Override
    public void authset(K var1, String var2, long var3, TimeUnit var5) {
        authRedis.opsForValue().set(var1.toString(), var2, var3, var5);
    }

    @Override
    public void ctrlset(K var1, String var2, long var3, TimeUnit var5) {
        ctrlRedis.opsForValue().set(var1.toString(), var2, var3, var5);
    }

    @Override
    public String authget(K var1) {
        return authRedis.opsForValue().get(var1);
    }

    @Override
    public String ctrlget(K var1) {
        return ctrlRedis.opsForValue().get(var1);
    }
}
