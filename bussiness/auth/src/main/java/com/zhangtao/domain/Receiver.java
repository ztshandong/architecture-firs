package com.zhangtao.domain;

/**
 * Created by zhangtao on 2017/7/18.
 */

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    @Qualifier("firstConnectionFactory")
    ConnectionFactory firstConnectionFactory;

    @Autowired
    @Qualifier("firstQueue")
    Queue firstQueue;

    @Bean(name = "firstmessageContainer")
    public SimpleMessageListenerContainer firstmessageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(firstConnectionFactory);
        container.setQueues(firstQueue);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {

            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                System.out.println("firstmessageContainer : " + new String(body));
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
            }
        });
        return container;
    }

    @RabbitHandler
    @RabbitListener(queues = "hello1", containerFactory = "firstFactory")
    public void process1(String hello) {
        System.out.println("Receiver hello1: " + hello);
    }
//
//    @RabbitHandler
//    @RabbitListener(queues = "hello2", containerFactory = "secondFactory")
//    public void process2(String hello) {
//        System.out.println("Receiver hello2: " + hello);
//    }
}