package com.zhangtao.config;

/**
 * Created by zhangtao on 2017/7/18.
 */

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Configuration
public class RabbitConfiguration {
    public static final String EXCHANGE = "rabbitmq-exchange";
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

    CachingConnectionFactory iniCachingConnectionFactory(String host, int port, String username, String password, boolean publisherconfirm) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }

    @Bean(name = "secondConnectionFactory")
    public ConnectionFactory secondConnectionFactory(

    ) {
        return iniCachingConnectionFactory(host2, port2, username2, password2, publisherconfirm2);
    }

    @Bean(name = "firstRabbitTemplate")
    @Primary
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate firstRabbitTemplate(
            @Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory
    ) {
        RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory);
        return firstRabbitTemplate;
    }

    @Bean(name = "secondRabbitTemplate")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate secondRabbitTemplate(
            @Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory
    ) {
        RabbitTemplate secondRabbitTemplate = new RabbitTemplate(connectionFactory);
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
        return new Queue(RabbitQueueEnum.queue1.getType(), true);
    }

    @Bean(name = "secondQueue")
    public Queue secondQueue() {
        return new Queue(RabbitQueueEnum.queue2.getType(), true);
    }

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean(name = "firstbinding")
    public Binding firstbinding() {
        return BindingBuilder.bind(firstQueue()).to(defaultExchange()).with(RabbitQueueEnum.queue1.getType());
    }

    @Bean(name = "secondbinding")
    public Binding secondbinding() {
        return BindingBuilder.bind(secondQueue()).to(defaultExchange()).with(RabbitQueueEnum.queue2.getType());
    }

    @Bean(name = "secondmessageContainer")
    public SimpleMessageListenerContainer secondmessageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(secondConnectionFactory());
        container.setQueues(secondQueue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {

            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                System.out.println("secondmessageContainer : " + new String(body));
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
            }
        });
        return container;
    }
}
