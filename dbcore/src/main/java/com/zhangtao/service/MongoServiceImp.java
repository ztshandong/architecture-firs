package com.zhangtao.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhangtao on 2017/7/16.
 */
@Service
public class MongoServiceImp implements MongoService {
    @Resource(name = "mongoLog1Template")
    public MongoTemplate mongoLog1;
    @Resource(name = "mongoLog2Template")
    public MongoTemplate mongoLog2;
}
