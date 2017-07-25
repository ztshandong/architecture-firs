package com.zhangtao.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangtao on 2017/7/20.
 */
//@Component
//@Repository
public interface RabbitMQReceiverService {
    boolean onMessageEx(Message message, Channel channel) throws Exception;
}
