package com.zhangtao.service;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.regexp.internal.RE;
import com.zhangtao.util.SpringContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangtao on 2017/7/16.
 */
@Service
public final class RedisServiceImp<K, V> implements RedisService<K, V> {
    private final static Log logger = LogFactory.getLog(RedisServiceImp.class);

    private StringRedisTemplate getAuthRedis() {
        if (authRedis == null)
            authRedis = (StringRedisTemplate) SpringContextUtil.getBean("authRedisTemplate");
        return authRedis;
    }

    public void setAuthRedis(StringRedisTemplate authRedis) {
        this.authRedis = authRedis;
    }

    private StringRedisTemplate getCtrlRedis() {
        if (ctrlRedis == null)
            ctrlRedis = (StringRedisTemplate) SpringContextUtil.getBean("ctrlRedisTemplate");
        return ctrlRedis;
    }

    public void setCtrlRedis(StringRedisTemplate ctrlRedis) {
        this.ctrlRedis = ctrlRedis;
    }

    @Autowired
    @Qualifier("authRedisTemplate")
    private StringRedisTemplate authRedis;

    @Autowired
    @Qualifier("ctrlRedisTemplate")
    private StringRedisTemplate ctrlRedis;

    @Override
    public boolean authhas(K key) {
        return getAuthRedis().hasKey(Objects.toString(key, ""));
    }

    @Override
    public boolean ctrlhas(K key) {
        return ctrlRedis.hasKey(Objects.toString(key, ""));
    }

    @Override
    public void authset(K var1, String var2, long var3, TimeUnit var5) {
//        System.out.println("调用String");
        getAuthRedis().opsForValue().set(Objects.toString(var1, ""), var2, var3, var5);
    }

    @Override
    public void ctrlset(K var1, String var2, long var3, TimeUnit var5) {
        getCtrlRedis().opsForValue().set(Objects.toString(var1, ""), var2, var3, var5);
    }

    @Override
    public String authget(K var1) {
        return getAuthRedis().opsForValue().get(Objects.toString(var1, ""));
    }

    @Override
    public String ctrlget(K var1) {
        return getCtrlRedis().opsForValue().get(Objects.toString(var1, ""));
    }

    @Override
    public void authset(K var1, V var2, long var3, TimeUnit var5) {
//        System.out.println("调用Json");
        getAuthRedis().opsForValue().set(Objects.toString(var1, ""), JSON.toJSONString(var2), var3, var5);
    }

    @Override
    public void ctrlset(K var1, V var2, long var3, TimeUnit var5) {
        getCtrlRedis().opsForValue().set(Objects.toString(var1, ""), JSON.toJSONString(var2), var3, var5);
    }

    @Override
    public V authget(K var1, Class<V> var2) {
        return JSON.parseObject(getAuthRedis().opsForValue().get(Objects.toString(var1, "")), var2);
    }

    @Override
    public V ctrlget(K var1, Class<V> var2) {
        return JSON.parseObject(getCtrlRedis().opsForValue().get(Objects.toString(var1, "")), var2);
    }

    @Override
    public Class<V> getClazz() {
//        Type t = getClass().getGenericSuperclass();
//        ParameterizedType p = (ParameterizedType) t;
//        Class<V> c = (Class<V>) p.getActualTypeArguments()[0];
//        return c;
        return (Class<V>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }
}
