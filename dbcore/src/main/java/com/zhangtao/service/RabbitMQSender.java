package com.zhangtao.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * Created by zhangtao on 2017/7/19.
 */
//@Component
public interface RabbitMQSender {

    void send1(String context, String CorrelationId)throws Exception;

    void send2(String context, String CorrelationId)throws Exception;

    void sendAll(String context, String CorrelationId)throws Exception;

}
