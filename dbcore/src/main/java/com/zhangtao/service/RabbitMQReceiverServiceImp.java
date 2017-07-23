package com.zhangtao.service;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtao on 2017/7/20.
 */
@Service
public abstract class RabbitMQReceiverServiceImp implements RabbitMQReceiverService {

    @Autowired
    @Qualifier("firstConnectionFactory")
    ConnectionFactory firstConnectionFactory;

    @Autowired
    @Qualifier("firstQueue")
    Queue firstQueue;

    @Autowired
    @Qualifier("secondConnectionFactory")
    ConnectionFactory secondConnectionFactory;

    @Autowired
    @Qualifier("secondQueue")
    Queue secondQueue;

    @Bean(name = "firstmessageContainer")
    public SimpleMessageListenerContainer firstmessageContainer() throws Exception {
        try {
            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(firstConnectionFactory);
            container.setQueues(firstQueue);
            container.setExposeListenerChannel(true);
            container.setMaxConcurrentConsumers(1);
            container.setConcurrentConsumers(1);
            container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
            container.setMessageListener(new ChannelAwareMessageListener() {

                @Override
                public void onMessage(Message message, Channel channel) throws Exception {
                    if (onMessageEx1(message, channel)) {
                        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
                    }
                }
            });
            return container;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Bean(name = "secondmessageContainer")
    public SimpleMessageListenerContainer secondmessageContainer() throws Exception {
        try {
            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(secondConnectionFactory);
            container.setQueues(secondQueue);
            container.setExposeListenerChannel(true);
            container.setMaxConcurrentConsumers(1);
            container.setConcurrentConsumers(1);
            container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
            container.setMessageListener(new ChannelAwareMessageListener() {

                @Override
                public void onMessage(Message message, Channel channel) throws Exception {
                    if (onMessageEx2(message, channel)) {
                        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
                    }
                }
            });
            return container;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
