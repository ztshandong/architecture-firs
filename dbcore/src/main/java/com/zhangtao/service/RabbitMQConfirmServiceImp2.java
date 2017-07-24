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
public abstract class RabbitMQConfirmServiceImp2 implements RabbitMQConfirmService, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private RabbitTemplate getSecondRabbitTemplate() {
        if (secondRabbitTemplate == null)
            secondRabbitTemplate = (RabbitTemplate) SpringContextUtil.getBean("secondRabbitTemplate");
        return secondRabbitTemplate;
    }

    public void setSecondRabbitTemplate(RabbitTemplate secondRabbitTemplate) {
        this.secondRabbitTemplate = secondRabbitTemplate;
    }

    @Resource(name = "secondRabbitTemplate")
    private RabbitTemplate secondRabbitTemplate;


    @Override
    public void send(String context, String CorrelationId) throws Exception {
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
