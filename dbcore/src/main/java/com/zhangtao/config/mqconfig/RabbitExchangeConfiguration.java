package com.zhangtao.config.mqconfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangtao on 2017/7/26.
 */
@Configuration
public class RabbitExchangeConfiguration {

    @Bean(name = "exchange1")
    public DirectExchange exchange1() {
        return new DirectExchange(RabbitExchangeEnum.exchange1.getType());
    }

    @Bean(name = "exchange2")
    public DirectExchange exchange2() {
        return new DirectExchange(RabbitExchangeEnum.exchange2.getType());
    }

    @Bean(name = "exchange3")
    public FanoutExchange exchange3() {
        return new FanoutExchange(RabbitExchangeEnum.exchange3.getType());
    }


    @Bean(name = "ex1Routing1Queue")
    public Queue ex1Routing1Queue() {
        return new Queue(RabbitRoutingKeyEnum.ex1Routing1.getType(), true);
    }

    @Bean(name = "ex1Routing2Queue")
    public Queue ex1Routing2Queue() {
        return new Queue(RabbitRoutingKeyEnum.ex1Routing2.getType(), true);
    }

    @Bean(name = "ex2Routing1Queue")
    public Queue ex2Routing1Queue() {
        return new Queue(RabbitRoutingKeyEnum.ex2Routing1.getType(), true);
    }

    @Bean(name = "ex2Routing2Queue")
    public Queue ex2Routing2Queue() {
        return new Queue(RabbitRoutingKeyEnum.ex2Routing2.getType(), true);
    }


    @Bean(name = "bindex1queue1route1")
    public Binding bindex1queue1route1() {
        return BindingBuilder.bind(ex1Routing1Queue()).to(exchange1()).with(RabbitRoutingKeyEnum.ex1Routing1.getType());
    }

    @Bean(name = "bindex1queue1route2")
    public Binding bindex1queue1route2() {
        return BindingBuilder.bind(ex1Routing2Queue()).to(exchange1()).with(RabbitRoutingKeyEnum.ex1Routing2.getType());
    }

    @Bean(name = "bindex2queue1route1")
    public Binding bindex2queue1route1() {
        return BindingBuilder.bind(ex2Routing1Queue()).to(exchange2()).with(RabbitRoutingKeyEnum.ex2Routing1.getType());
    }

    @Bean(name = "bindex2queue2route2")
    public Binding bindex2queue2route2() {
        return BindingBuilder.bind(ex2Routing2Queue()).to(exchange2()).with(RabbitRoutingKeyEnum.ex2Routing2.getType());
    }


    @Bean(name = "bindFanoutExchange1")
    public Binding bindFanoutExchange1() {
        return BindingBuilder.bind(ex1Routing1Queue()).to(exchange3());
    }

    @Bean(name = "bindFanoutExchange2")
    public Binding bindFanoutExchange2() {
        return BindingBuilder.bind(ex1Routing2Queue()).to(exchange3());
    }

    @Bean(name = "bindFanoutExchange3")
    public Binding bindFanoutExchange3() {
        return BindingBuilder.bind(ex2Routing1Queue()).to(exchange3());
    }

    @Bean(name = "bindFanoutExchange4")
    public Binding bindFanoutExchange4() {
        return BindingBuilder.bind(ex2Routing2Queue()).to(exchange3());
    }

}
