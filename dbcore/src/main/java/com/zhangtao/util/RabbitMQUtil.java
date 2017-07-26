package com.zhangtao.util;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

/**
 * Created by zhangtao on 2017/7/26.
 */
public final class RabbitMQUtil {
    public static CachingConnectionFactory getCachingConnectionFactory(String host, int port, String username, String password, boolean publisherconfirm, boolean publisherreturns) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPublisherConfirms(publisherconfirm);
        connectionFactory.setPublisherReturns(publisherreturns);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setChannelCacheSize(1);
        return connectionFactory;
    }
    public static SimpleMessageListenerContainer getSimpleMessageListenerContainer(ConnectionFactory connectionFactory, Queue queue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setPrefetchCount(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        return container;
    }
}
