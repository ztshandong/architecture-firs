package com.zhangtao.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangtao on 2017/7/19.
 */
//@Component
@Repository
public interface RabbitMQSenderService {

    void send(String context, String CorrelationId) throws Exception;

}
