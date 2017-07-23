package com.zhangtao.service;

import com.zhangtao.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Created by zhangtao on 2017/7/16.
 */
@Service
public final class MongoServiceImp<T> implements MongoService<T> {
    private MongoTemplate getMongoLog1() {
        if (mongoLog1 == null)
            mongoLog1 = (MongoTemplate) SpringContextUtil.getBean("mongoLog1Template");
        return mongoLog1;
    }

    public void setMongoLog1(MongoTemplate mongoLog1) {
        this.mongoLog1 = mongoLog1;
    }

    private MongoTemplate getMongoLog2() {
        if (mongoLog2 == null)
            mongoLog2 = (MongoTemplate) SpringContextUtil.getBean("mongoLog2Template");
        return mongoLog2;
    }

    public void setMongoLog2(MongoTemplate mongoLog2) {
        this.mongoLog2 = mongoLog2;
    }

    @Autowired
    @Resource(name = "mongoLog1Template")
    private MongoTemplate mongoLog1;
    @Autowired
    @Resource(name = "mongoLog2Template")
    private MongoTemplate mongoLog2;

    @Override
    public void mongo1save(T object) throws Exception {
        try {
            getMongoLog1().save(object);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mongo2save(T object) throws Exception {
        try {
            getMongoLog2().save(object);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mongo1save(T object, String collectionName) throws Exception {
        try {
            getMongoLog1().save(object, collectionName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mongo2save(T object, String collectionName) throws Exception {
        try {
            getMongoLog2().save(object, collectionName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
