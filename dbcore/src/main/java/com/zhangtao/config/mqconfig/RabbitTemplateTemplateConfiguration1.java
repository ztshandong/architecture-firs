package com.zhangtao.config.mqconfig;

/**
 * Created by zhangtao on 2017/7/18.
 */

import com.zhangtao.util.RabbitMQUtil;
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
public class RabbitTemplateTemplateConfiguration1 {

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
    @Value("${rabbitmq.first.publisherreturns}")
    boolean publisherreturns1;

    @Bean(name = "firstConnectionFactory")
    @Primary
    public ConnectionFactory firstConnectionFactory() {
        return RabbitMQUtil.getCachingConnectionFactory(host1, port1, username1, password1, publisherconfirm1, publisherreturns1);
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

    @Bean(name = "firstFactory")
    public SimpleRabbitListenerContainerFactory firstFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }


}
