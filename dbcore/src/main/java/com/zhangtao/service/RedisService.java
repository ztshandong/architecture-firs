package com.zhangtao.service;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangtao on 2017/7/16.
 */
public interface RedisService<K, V> {
    boolean authhas(K key);

    boolean ctrlhas(K key);

    void authset(K var1, String var2, long var3, TimeUnit var5);

    void ctrlset(K var1, String var2, long var3, TimeUnit var5);

    String authget(K var1);

    String ctrlget(K var1);

    void authset(K var1, V var2, long var3, TimeUnit var5);

    void ctrlset(K var1, V var2, long var3, TimeUnit var5);

    V authget(K var1, Class<V> var2);

    V ctrlget(K var1, Class<V> var2);

    Class<V> getClazz();
}
