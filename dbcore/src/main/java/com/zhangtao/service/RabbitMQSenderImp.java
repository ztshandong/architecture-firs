package com.zhangtao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtao on 2017/7/24.
 */
@Service
public class RabbitMQSenderImp implements RabbitMQSender {

    @Autowired
    private RabbitMQConfirm1 rabbitMQConfirmService1 =new RabbitMQConfirm1() ;
    @Autowired
    private RabbitMQConfirm2 rabbitMQConfirmService2=new RabbitMQConfirm2();

    @Override
    public void send1(String context, String CorrelationId) throws Exception {
        rabbitMQConfirmService1.send(context, CorrelationId);
    }

    @Override
    public void send2(String context, String CorrelationId) throws Exception {
        rabbitMQConfirmService2.send(context, CorrelationId);
    }

    @Override
    public void sendAll(String context, String CorrelationId) throws Exception {
        rabbitMQConfirmService1.send(context, CorrelationId);
        rabbitMQConfirmService2.send(context, CorrelationId);

    }
}
