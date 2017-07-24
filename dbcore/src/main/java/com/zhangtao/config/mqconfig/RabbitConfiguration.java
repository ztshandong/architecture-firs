package com.zhangtao.config.mqconfig;

/**
 * Created by zhangtao on 2017/7/18.
 */

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitConfiguration {

    @Value("${rabbitmq.first.host}")
    String host1;
    @Value("${rabbitmq.first.port}")
    int port1;
    @Value("${rabbitmq.first.username}")
    String username1;
    @Value("${rabbitmq.first.password}")
    String password1;
    @Value("${rabbitmq.first.publisherconfirm}")
    boolean publisherconfirm1;

    @Value("${rabbitmq.second.host}")
    String host2;
    @Value("${rabbitmq.second.port}")
    int port2;
    @Value("${rabbitmq.second.username}")
    String username2;
    @Value("${rabbitmq.second.password}")
    String password2;
    @Value("${rabbitmq.second.publisherconfirm}")
    boolean publisherconfirm2;

    @Bean(name = "firstConnectionFactory")
    @Primary
    public ConnectionFactory firstConnectionFactory() {
        return iniCachingConnectionFactory(host1, port1, username1, password1, publisherconfirm1);
    }

    @Bean(name = "secondConnectionFactory")
    public ConnectionFactory secondConnectionFactory() {
        return iniCachingConnectionFactory(host2, port2, username2, password2, publisherconfirm2);
    }

    CachingConnectionFactory iniCachingConnectionFactory(String host, int port, String username, String password, boolean publisherconfirm) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPublisherConfirms(publisherconfirm);
        connectionFactory.setPublisherReturns(true);
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }

    @Bean(name = "firstRabbitTemplate")
    @Primary
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate firstRabbitTemplate(
            @Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory
    ) {
        RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory);
        firstRabbitTemplate.setMandatory(true);
        return firstRabbitTemplate;
    }

    @Bean(name = "secondRabbitTemplate")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate secondRabbitTemplate(
            @Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory
    ) {
        RabbitTemplate secondRabbitTemplate = new RabbitTemplate(connectionFactory);
        secondRabbitTemplate.setMandatory(true);
        return secondRabbitTemplate;
    }

    @Bean(name = "firstFactory")
    public SimpleRabbitListenerContainerFactory firstFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean(name = "secondFactory")
    public SimpleRabbitListenerContainerFactory secondFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean(name = "firstQueue")
    public Queue firstQueue() {
        return new Queue(RabbitRoutingKeyEnum.routing1.getType(), true);
    }

    @Bean(name = "secondQueue")
    public Queue secondQueue() {
        return new Queue(RabbitRoutingKeyEnum.routing2.getType(), true);
    }

    @Bean(name = "firstExchange")
    public DirectExchange firstExchange() {
        return new DirectExchange(RabbitExchangeEnum.exchange1.getType());
    }

    @Bean(name = "secondExchange")
    public DirectExchange secondExchange() {
        return new DirectExchange(RabbitExchangeEnum.exchange2.getType());
    }

    @Bean(name = "firstbinding")
    public Binding firstbinding() {
        return BindingBuilder.bind(firstQueue()).to(firstExchange()).with(RabbitRoutingKeyEnum.routing1.getType());
    }

    @Bean(name = "secondbinding")
    public Binding secondbinding() {
        return BindingBuilder.bind(secondQueue()).to(secondExchange()).with(RabbitRoutingKeyEnum.routing2.getType());
    }

}
