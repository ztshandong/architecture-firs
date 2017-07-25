package com.zhangtao.service;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zhangtao.domain.AopMongoLog;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtao on 2017/7/24.
 */
@Service("receiverServiceImp1")
public class ReceiverServiceImp1 extends RabbitMQReceiverServiceImp1 implements RabbitMQReceiverService {
    @Autowired
    private MongoService<AopMongoLog> mongoService;

    @Override
    public boolean onMessageEx(Message message, Channel channel) throws Exception {
        try {
            String s1 = JSON.toJSONString(message);
            String s2 = JSON.toJSONString(channel);
            byte[] body = message.getBody();
            String mongojson = new String(body);
            AopMongoLog aopMongoLog = JSON.parseObject(mongojson, AopMongoLog.class);
            mongoService.mongo2save(aopMongoLog);
            System.out.println("ReceiverServiceImp1成功消费 : " + mongojson);
            System.out.println("message : " + s1);
            System.out.println("channel : " + s2);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

