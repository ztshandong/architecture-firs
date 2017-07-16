package com.zhangtao.service;

import java.util.Objects;

/**
 * Created by zhangtao on 2017/7/16.
 */

public interface MongoService<T> {
    void mongo1save(T object);
    void mongo2save(T object);
}
