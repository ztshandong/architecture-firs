//package com.zhangtao.domain;
//
///**
// * Created by zhangtao on 2017/7/18.
// */
//
//import java.util.Date;
//import java.util.Objects;
//import java.util.UUID;
//import javax.annotation.Resource;
//
//import com.rabbitmq.client.AMQP;
//import com.zhangtao.config.mqconfig.RabbitExchangeEnum;
//import com.zhangtao.config.mqconfig.RabbitRoutingKeyEnum;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Sender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
//
//    @Resource(name = "firstRabbitTemplate")
//    private RabbitTemplate firstRabbitTemplate;
//
//    @Resource(name = "secondRabbitTemplate")
//    private RabbitTemplate secondRabbitTemplate;
//
////    @Autowired
////    public Sender(RabbitTemplate firstRabbitTemplate, RabbitTemplate secondRabbitTemplate) {
////        this.firstRabbitTemplate = firstRabbitTemplate;
////        this.secondRabbitTemplate = secondRabbitTemplate;
////        firstRabbitTemplate.setConfirmCallback(this);
////        secondRabbitTemplate.setConfirmCallback(this);
////    }
//
//    public void send1(String str) {
//        String context = str;
//        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//        System.out.println("Sender1 : " + context);
//        firstRabbitTemplate.setConfirmCallback(this);
//        firstRabbitTemplate.setReturnCallback(this);
//        this.firstRabbitTemplate.convertAndSend(RabbitExchangeEnum.exchange1.getType(), RabbitRoutingKeyEnum.routing1.getType(), context, correlationId);
//    }
//
//    public void send2(String str) {
//        String context = str + new Date();
//        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//        System.out.println("Sender2 : " + context);
//        secondRabbitTemplate.setConfirmCallback(this);
//        secondRabbitTemplate.setReturnCallback(this);
//        this.secondRabbitTemplate.convertAndSend(RabbitExchangeEnum.exchange2.getType(), RabbitRoutingKeyEnum.routing2.getType(), context, correlationId);
//    }
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//
//        System.out.println(" 回调id:" + correlationData);
//        if (ack) {
//            System.out.println("消息成功送达");
//        } else {
//            System.out.println("消息发送失败:" + cause);
//        }
//    }
//
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        System.out.println("================");
//        System.out.println("message = " + message);
//        System.out.println("replyCode = " + replyCode);
//        System.out.println("replyText = " + replyText);
//        System.out.println("exchange = " + exchange);
//        System.out.println("routingKey = " + routingKey);
//        System.out.println("================");
//    }
//}
