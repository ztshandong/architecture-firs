package com.zhangtao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtao on 2017/7/24.
 */
@Service
public class RabbitMQSenderServiceImp implements RabbitMQSenderService {

    public RabbitMQSenderServiceImp() {
        rabbitMQConfirmService1 = new RabbitMQConfirmServiceImp1();
        rabbitMQConfirmService2 = new RabbitMQConfirmServiceImp2();
//        rabbitMQConfirmService1.init();
    }

    //    @Autowired
    private RabbitMQConfirmServiceImp1 rabbitMQConfirmService1;
    private RabbitMQConfirmServiceImp2 rabbitMQConfirmService2;
//    @Autowired
//    private RabbitMQConfirm2 rabbitMQConfirmService2=new RabbitMQConfirm2();

    @Override
    public void send1(String context, String CorrelationId) throws Exception {
//        System.out.println("rabbitMQConfirmService1" + rabbitMQConfirmService1);
        rabbitMQConfirmService1.init();
        rabbitMQConfirmService1.send(context, CorrelationId);
    }

    @Override
    public void send2(String context, String CorrelationId) throws Exception {
        rabbitMQConfirmService2.init();
        rabbitMQConfirmService2.send(context, CorrelationId);
    }

    @Override
    public void sendAll(String context, String CorrelationId) throws Exception {
//        System.out.println("rabbitMQConfirmService1" + rabbitMQConfirmService1);
        rabbitMQConfirmService1.init();
        rabbitMQConfirmService1.send(context, CorrelationId);
        rabbitMQConfirmService2.init();
        rabbitMQConfirmService2.send(context, CorrelationId);

    }
}