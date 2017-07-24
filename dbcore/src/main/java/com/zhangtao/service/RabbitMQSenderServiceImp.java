package com.zhangtao.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhangtao.config.mqconfig.RabbitExchangeEnum;
import com.zhangtao.config.mqconfig.RabbitRoutingKeyEnum;
import com.zhangtao.util.SpringContextUtil;
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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zhangtao on 2017/7/19.
 */
@Service
public abstract class RabbitMQSenderServiceImp implements RabbitMQSenderService, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private RabbitTemplate getFirstRabbitTemplate() {
        if (firstRabbitTemplate == null)
            firstRabbitTemplate = (RabbitTemplate) SpringContextUtil.getBean("firstRabbitTemplate");
        return firstRabbitTemplate;
    }

    public void setFirstRabbitTemplate(RabbitTemplate firstRabbitTemplate) {
        this.firstRabbitTemplate = firstRabbitTemplate;
    }

    private RabbitTemplate getSecondRabbitTemplate() {
        if (secondRabbitTemplate == null)
            secondRabbitTemplate = (RabbitTemplate) SpringContextUtil.getBean("secondRabbitTemplate");
        return secondRabbitTemplate;
    }

    public void setSecondRabbitTemplate(RabbitTemplate secondRabbitTemplate) {
        this.secondRabbitTemplate = secondRabbitTemplate;
    }

    @Resource(name = "firstRabbitTemplate")
    private RabbitTemplate firstRabbitTemplate;

    @Resource(name = "secondRabbitTemplate")
    private RabbitTemplate secondRabbitTemplate;
//
//    @Override
//    public void confirmEx(CorrelationData correlationData, boolean ack, String cause) {
//        System.out.println("RabbitMQSenderServiceImpSenderConfirmEx");
//    }
//
//    @Override
//    public void returnedMessageEx(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        System.out.println("RabbitMQSenderServiceImpSenderreturnedMessageEx");
//    }

    @Override
    public void send1(String context, String CorrelationId) throws Exception {
        try {
            CorrelationData correlationId = new CorrelationData(CorrelationId);
            System.out.println("sender1:"+CorrelationId);
            getFirstRabbitTemplate().setConfirmCallback(this);
            getFirstRabbitTemplate().setReturnCallback(this);
            this.getFirstRabbitTemplate().convertAndSend(RabbitExchangeEnum.exchange1.getType(), RabbitRoutingKeyEnum.routing1.getType(), context, correlationId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void send2(String context, String CorrelationId) throws Exception {
        try {
            CorrelationData correlationId = new CorrelationData(CorrelationId);
            System.out.println("sender2:"+CorrelationId);
            getSecondRabbitTemplate().setConfirmCallback(this);
            getSecondRabbitTemplate().setReturnCallback(this);
            this.getSecondRabbitTemplate().convertAndSend(RabbitExchangeEnum.exchange2.getType(), RabbitRoutingKeyEnum.routing2.getType(), context, correlationId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
