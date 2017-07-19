package com.zhangtao.service;

import com.rabbitmq.client.Channel;
import com.zhangtao.config.mqconfig.RabbitExchangeEnum;
import com.zhangtao.config.mqconfig.RabbitRoutingKeyEnum;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zhangtao on 2017/7/19.
 */
@Service
public abstract class RabbitMQSenderServiceImp implements RabbitMQSenderService, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Resource(name = "firstRabbitTemplate")
    private RabbitTemplate firstRabbitTemplate;

    @Resource(name = "secondRabbitTemplate")
    private RabbitTemplate secondRabbitTemplate;

    //UUID.randomUUID().toString()
    @Override
    public void send1(String context, String CorrelationId) {
        CorrelationData correlationId = new CorrelationData(CorrelationId);
        firstRabbitTemplate.setConfirmCallback(this);
        firstRabbitTemplate.setReturnCallback(this);
        this.firstRabbitTemplate.convertAndSend(RabbitExchangeEnum.exchange1.getType(), RabbitRoutingKeyEnum.routing1.getType(), context, correlationId);
    }

    @Override
    public void send2(String context, String CorrelationId) {
        CorrelationData correlationId = new CorrelationData(CorrelationId);
        secondRabbitTemplate.setConfirmCallback(this);
        secondRabbitTemplate.setReturnCallback(this);
        this.secondRabbitTemplate.convertAndSend(RabbitExchangeEnum.exchange2.getType(), RabbitRoutingKeyEnum.routing2.getType(), context, correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        confirmEx(correlationData, ack, cause);

    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        returnedMessageEx(message, replyCode, replyText, exchange, routingKey);
    }


}
