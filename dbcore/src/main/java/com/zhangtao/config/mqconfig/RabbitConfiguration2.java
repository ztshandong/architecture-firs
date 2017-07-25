package com.zhangtao.config.mqconfig;

/**
 * Created by zhangtao on 2017/7/18.
 */

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
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
public class RabbitConfiguration2 extends RabbitConfigurationBase {

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
    @Value("${rabbitmq.first.publisherreturns}")
    boolean publisherreturns2;

    @Bean(name = "secondConnectionFactory")
    public ConnectionFactory secondConnectionFactory() {
        return iniCachingConnectionFactory(host2, port2, username2, password2, publisherconfirm2, publisherreturns2);
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

    @Bean(name = "secondFactory")
    public SimpleRabbitListenerContainerFactory secondFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean(name = "secondQueue")
    public Queue secondQueue() {
        return new Queue(RabbitRoutingKeyEnum.routing2.getType(), true);
    }

    @Bean(name = "secondExchange")
    public DirectExchange secondExchange() {
        return new DirectExchange(RabbitExchangeEnum.exchange2.getType());
    }

    @Bean(name = "secondbinding")
    public Binding secondbinding() {
        return BindingBuilder.bind(secondQueue()).to(secondExchange()).with(RabbitRoutingKeyEnum.routing2.getType());
    }

}
