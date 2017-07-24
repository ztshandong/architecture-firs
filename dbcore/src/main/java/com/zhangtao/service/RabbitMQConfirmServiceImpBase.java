package com.zhangtao.service;

import com.zhangtao.config.mqconfig.RabbitExchangeEnum;
import com.zhangtao.config.mqconfig.RabbitRoutingKeyEnum;
import com.zhangtao.util.SpringContextUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhangtao on 2017/7/19.
 */
@Service
public abstract class RabbitMQConfirmServiceImpBase implements RabbitMQConfirmService, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRountingkey() {
        return rountingkey;
    }

    public void setRountingkey(String rountingkey) {
        this.rountingkey = rountingkey;
    }

    protected void initRabbitTemplate() {
        rabbitTemplate = new RabbitTemplate();
    }

    //    @Autowired
    protected RabbitTemplate rabbitTemplate;

    //    @Autowired
    protected String exchange = "";

    //    @Autowired
    protected String rountingkey = "";

    //    @Override
    public void send(String context, String CorrelationId) throws Exception {
        try {
            CorrelationData correlationId = new CorrelationData(CorrelationId);
            System.out.println("sender:" + CorrelationId);
            getRabbitTemplate().setConfirmCallback(this);
            getRabbitTemplate().setReturnCallback(this);
            this.getRabbitTemplate().convertAndSend(getExchange(), getRountingkey(), context, correlationId);
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
