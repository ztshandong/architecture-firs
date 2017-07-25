package com.zhangtao.service;

import com.rabbitmq.client.Channel;
import com.zhangtao.util.SpringContextUtil;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtao on 2017/7/20.
 */
@Service("rabbitMQReceiverServiceImp2")
public abstract class RabbitMQReceiverServiceImp2 implements RabbitMQReceiverService {

    private ConnectionFactory getSecondConnectionFactory() {
        if (secondConnectionFactory == null)
            secondConnectionFactory = (ConnectionFactory) SpringContextUtil.getBean("secondConnectionFactory");
        return secondConnectionFactory;
    }

    private Queue getSecondQueue() {
        if (secondQueue == null)
            secondQueue = (Queue) SpringContextUtil.getBean("secondQueue");
        return secondQueue;
    }

    @Autowired
    @Qualifier("secondConnectionFactory")
    private ConnectionFactory secondConnectionFactory;

    @Autowired
    @Qualifier("secondQueue")
    private Queue secondQueue;

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
//
//    @Override
//    public boolean onMessageEx(Message message, Channel channel) throws Exception {
//        return false;
//    }
}
