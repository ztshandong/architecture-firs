package com.zhangtao.config.dbconfig;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Created by zhangtao on 2017/6/30.
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {
    @Value("${spring.redis.auth.host}")
    private String authhost;

    @Value("${spring.redis.auth.port}")
    private int authport;

    @Value("${spring.redis.auth.database}")
    private int authdatabase;

    @Primary
    @Bean(name = "authRedisConnectionFactory")
//    @Bean
    public RedisConnectionFactory authRedisConnectionFactory() {

        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(authhost);
        redisConnectionFactory.setPort(authport);
        redisConnectionFactory.setDatabase(authdatabase);
        return redisConnectionFactory;
    }

    @Bean(name = "authRedisTemplate")
//    @Bean
    public StringRedisTemplate authRedisTemplate(@Qualifier("authRedisConnectionFactory") RedisConnectionFactory cf) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(cf);
        return stringRedisTemplate;
    }


    @Value("${spring.redis.ctrl.host}")
    private String ctrlhost;

    @Value("${spring.redis.ctrl.port}")
    private int ctrlport;

    @Value("${spring.redis.ctrl.database}")
    private int ctrldatabase;

    @Bean(name = "ctrlRedisConnectionFactory")
    public RedisConnectionFactory ctrlRedisConnectionFactory() {

        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(ctrlhost);
        redisConnectionFactory.setPort(ctrlport);
        redisConnectionFactory.setDatabase(ctrldatabase);
        return redisConnectionFactory;
    }

    @Bean(name = "ctrlRedisTemplate")
    public StringRedisTemplate ctrlStringRedisTemplate(@Qualifier("ctrlRedisConnectionFactory") RedisConnectionFactory cf) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(cf);
        return stringRedisTemplate;
    }

}
