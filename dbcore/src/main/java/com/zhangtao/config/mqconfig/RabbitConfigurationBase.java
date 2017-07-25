package com.zhangtao.config.mqconfig;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;

/**
 * Created by zhangtao on 2017/7/25.
 */
public class RabbitConfigurationBase {

   protected CachingConnectionFactory iniCachingConnectionFactory(String host, int port, String username, String password, boolean publisherconfirm, boolean publisherreturns) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPublisherConfirms(publisherconfirm);
        connectionFactory.setPublisherReturns(publisherreturns);
        connectionFactory.setVirtualHost("/");

        return connectionFactory;
    }
}
