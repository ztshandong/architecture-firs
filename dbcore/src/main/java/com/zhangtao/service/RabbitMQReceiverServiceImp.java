package com.zhangtao.service;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zhangtao.util.SpringContextUtil;
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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtao on 2017/7/20.
 */
@Service
public abstract class RabbitMQReceiverServiceImp implements RabbitMQReceiverService {

    private ConnectionFactory getFirstConnectionFactory() {
        if (firstConnectionFactory == null)
            firstConnectionFactory = (ConnectionFactory) SpringContextUtil.getBean("firstConnectionFactory");
        return firstConnectionFactory;
    }

    public void setFirstConnectionFactory(ConnectionFactory firstConnectionFactory) {
        this.firstConnectionFactory = firstConnectionFactory;
    }

    private Queue getFirstQueue() {
        if (firstQueue == null)
            firstQueue = (Queue) SpringContextUtil.getBean("firstQueue");
        return firstQueue;
    }

    public void setFirstQueue(Queue firstQueue) {
        this.firstQueue = firstQueue;
    }

    private ConnectionFactory getSecondConnectionFactory() {
        if (secondConnectionFactory == null)
            secondConnectionFactory = (ConnectionFactory) SpringContextUtil.getBean("secondConnectionFactory");
        return secondConnectionFactory;
    }

    public void setSecondConnectionFactory(ConnectionFactory secondConnectionFactory) {
        this.secondConnectionFactory = secondConnectionFactory;
    }

    private Queue getSecondQueue() {
        if (secondQueue == null)
            secondQueue = (Queue) SpringContextUtil.getBean("secondQueue");
        return secondQueue;
    }

    public void setSecondQueue(Queue secondQueue) {
        this.secondQueue = secondQueue;
    }

    @Autowired
    @Qualifier("firstConnectionFactory")
    private ConnectionFactory firstConnectionFactory;

    @Autowired
    @Qualifier("firstQueue")
    private Queue firstQueue;

    @Autowired
    @Qualifier("secondConnectionFactory")
    private ConnectionFactory secondConnectionFactory;

    @Autowired
    @Qualifier("secondQueue")
    private Queue secondQueue;

    @Bean(name = "firstmessageContainer")
    public SimpleMessageListenerContainer firstmessageContainer() throws Exception {
        try {
            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(getFirstConnectionFactory());
            container.setQueues(getFirstQueue());
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
            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(getSecondConnectionFactory());
            container.setQueues(getSecondQueue());
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
//
//    @Override
//    public boolean onMessageEx1(Message message, Channel channel) throws Exception {
//        return false;
//    }
//
//    @Override
//    public boolean onMessageEx2(Message message, Channel channel) throws Exception {
//        return false;
//    }
}
