package com.zhangtao.service;

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
                System.out.println("first成功消费 : " + new String(body));
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
            }
        });
        return container;
    }

    @Bean(name = "secondmessageContainer")
    public SimpleMessageListenerContainer secondmessageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(secondConnectionFactory);
        container.setQueues(secondQueue);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {

            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                System.out.println("second成功消费 : " + new String(body));
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
            }
        });
        return container;
    }
}
