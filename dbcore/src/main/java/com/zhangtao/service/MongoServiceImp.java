package com.zhangtao.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhangtao on 2017/7/16.
 */
@Service
public class MongoServiceImp<T> implements MongoService<T> {
    @Resource(name = "mongoLog1Template")
    public MongoTemplate mongoLog1;
    @Resource(name = "mongoLog2Template")
    public MongoTemplate mongoLog2;

    @Override
    public void mongo1save(T object) {
        mongoLog1.save(object);
    }

    @Override
    public void mongo2save(T object) {
        mongoLog2.save(object);
    }
}
