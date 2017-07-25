package com.zhangtao.service;

import com.rabbitmq.client.Channel;
import com.zhangtao.config.mqconfig.RabbitExchangeEnum;
import com.zhangtao.util.SpringContextUtil;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtao on 2017/7/20.
 */
@Service("rabbitMQReceiverServiceImp1")
public abstract class RabbitMQReceiverServiceImp1 implements RabbitMQReceiverService {

    private ConnectionFactory getFirstConnectionFactory() {
        if (firstConnectionFactory == null)
            firstConnectionFactory = (ConnectionFactory) SpringContextUtil.getBean("firstConnectionFactory");
        return firstConnectionFactory;
    }

    private Queue getFirstQueue() {
        if (firstQueue == null)
            firstQueue = (Queue) SpringContextUtil.getBean("firstQueue");
        return firstQueue;
    }

    @Autowired
    @Qualifier("firstConnectionFactory")
    private ConnectionFactory firstConnectionFactory;

    @Autowired
    @Qualifier("firstQueue")
    private Queue firstQueue;

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
                    channel.basicQos(1);
                    if (onMessageEx(message, channel)) {
                        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
                    }
                    else {
                        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
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
