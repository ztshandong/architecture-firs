package com.zhangtao.service;

import com.zhangtao.config.mqconfig.RabbitExchangeEnum;
import com.zhangtao.config.mqconfig.RabbitRoutingKeyEnum;
import com.zhangtao.util.SpringContextUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhangtao on 2017/7/19.
 */
@Service
public abstract class RabbitMQConfirmServiceImp1 implements RabbitMQConfirmService, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private RabbitTemplate getFirstRabbitTemplate() {
        if (firstRabbitTemplate == null)
            firstRabbitTemplate = (RabbitTemplate) SpringContextUtil.getBean("firstRabbitTemplate");
        return firstRabbitTemplate;
    }

    public void setFirstRabbitTemplate(RabbitTemplate firstRabbitTemplate) {
        this.firstRabbitTemplate = firstRabbitTemplate;
    }

    @Resource(name = "firstRabbitTemplate")
    private RabbitTemplate firstRabbitTemplate;


    @Override
    public void send(String context, String CorrelationId) throws Exception {
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
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        confirmEx(correlationData, ack, cause);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        returnedMessageEx(message, replyCode, replyText, exchange, routingKey);
    }


}
