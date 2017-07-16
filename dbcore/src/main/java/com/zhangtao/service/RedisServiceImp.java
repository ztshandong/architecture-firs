package com.zhangtao.service;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtao on 2017/7/16.
 */
@Service
public class RedisServiceImp implements RedisService{
    private static Log logger = LogFactory.getLog(RedisServiceImp.class);

    @Autowired
    @Qualifier("authRedisTemplate")
    public StringRedisTemplate authRedis;

    @Autowired
    @Qualifier("ctrlRedisTemplate")
    public StringRedisTemplate ctrlRedis;


    public ValueOperations<String, String> authops;
    public ValueOperations<String, String> ctrlops;

    public void IniRedis() {
        if (authops == null)
            authops = authRedis.opsForValue();
        if (ctrlops == null)
            ctrlops = ctrlRedis.opsForValue();

    }
}
