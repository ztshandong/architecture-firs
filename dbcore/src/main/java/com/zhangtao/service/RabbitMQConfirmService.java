package com.zhangtao.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * Created by zhangtao on 2017/7/19.
 */
//@Component
public interface RabbitMQConfirmService {

    void confirmEx(CorrelationData correlationData, boolean ack, String cause) ;

    void returnedMessageEx(Message message, int replyCode, String replyText, String exchange, String routingKey);

    void send(String context, String CorrelationId)throws Exception;

}
