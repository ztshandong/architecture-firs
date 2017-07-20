package com.zhangtao.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Created by zhangtao on 2017/7/20.
 */
public interface RabbitMQReceiverService {
    boolean onMessageEx1(Message message, Channel channel) throws Exception;

    boolean onMessageEx2(Message message, Channel channel) throws Exception;
}
