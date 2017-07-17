package com.zhangtao.service;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangtao on 2017/7/16.
 */
public interface RedisService<Object,String> {
    boolean authhas(Object key);
    boolean ctrlhas(Object key);

    void authset(Object var1, String var2, long var3, TimeUnit var5);
    void ctrlset(Object var1, String var2, long var3, TimeUnit var5);

    String authget(Object var1);
    String ctrlget(Object var1);
}
