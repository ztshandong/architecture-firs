//package com.zhangtao.service;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.stereotype.Service;
//
///**
// * Created by zhangtao on 2017/7/24.
// */
//@Service
//public class AopSqlSenderServiceImp extends RabbitMQSenderServiceImp implements AopSqlSenderService {
//
//    @Override
//    public void confirmEx(CorrelationData correlationData, boolean ack, String cause) {
//        if (ack) {
//            System.out.println("dbcore发送成功:" + correlationData);
//        } else {
//            System.out.println("消息发送失败:" + cause);
//        }
//    }
//
//    @Override
//    public void returnedMessageEx(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        System.out.println("================");
//        System.out.println("message = " + message);
//        System.out.println("replyCode = " + replyCode);
//        System.out.println("replyText = " + replyText);
//        System.out.println("exchange = " + exchange);
//        System.out.println("routingKey = " + routingKey);
//        System.out.println("================");
//    }
//
//}
