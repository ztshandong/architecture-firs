package com.zhangtao.service;

import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by zhangtao on 2017/7/16.
 */
//@Component
public interface MongoService<T> {
    void mongo1save(T object)throws Exception;
    void mongo2save(T object)throws Exception;
}
