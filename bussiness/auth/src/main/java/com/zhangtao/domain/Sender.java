package com.zhangtao.domain;

/**
 * Created by zhangtao on 2017/7/18.
 */

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Resource;

import com.zhangtao.config.RabbitConfiguration;
import com.zhangtao.config.RabbitQueueEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Sender implements RabbitTemplate.ConfirmCallback {

    @Resource(name = "firstRabbitTemplate")
    private RabbitTemplate firstRabbitTemplate;

    @Resource(name = "secondRabbitTemplate")
    private RabbitTemplate secondRabbitTemplate;

//    @Autowired
//    public Sender(RabbitTemplate firstRabbitTemplate, RabbitTemplate secondRabbitTemplate) {
//        this.firstRabbitTemplate = firstRabbitTemplate;
//        this.secondRabbitTemplate = secondRabbitTemplate;
//        firstRabbitTemplate.setConfirmCallback(this);
//        secondRabbitTemplate.setConfirmCallback(this);
//    }

    public void send1(String str) {
        String context = str;
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("Sender1 : " + context);
        firstRabbitTemplate.setConfirmCallback(this);
        this.firstRabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitQueueEnum.queue1.getType(), context, correlationId);
    }

    public void send2(String str) {
        String context = str + new Date();
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("Sender2 : " + context);
        secondRabbitTemplate.setConfirmCallback(this);
        this.secondRabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitQueueEnum.queue2.getType(), context, correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        System.out.println(" 回调id:" + correlationData);
        if (ack) {
            System.out.println("消息成功送达");
        } else {
            System.out.println("消息发送失败:" + cause);
        }
    }
}
