//package com.zhangtao.service;
//
//import com.alibaba.fastjson.JSON;
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
///**
// * Created by zhangtao on 2017/7/20.
// */
//@Service("receiverServiceImp3")
//public class ReceiverServiceImp3 extends RabbitMQReceiverServiceImp1 implements RabbitMQReceiverService {
//
//    @Override
//    public boolean onMessageEx(Message message, Channel channel) throws Exception {
//        try {
//            byte[] body = message.getBody();
//            System.out.println("ReceiverServiceImp3成功接收 : " + new String(body));
//            String s1 = JSON.toJSONString(message);
//            String s2 = JSON.toJSONString(channel);
//            System.out.println("message : " + s1);
//            System.out.println("channel : " + s2);
//            return true;
//        } catch (Exception ex) {
////            ex.printStackTrace();
//            return false;
//        }
//    }
//}
