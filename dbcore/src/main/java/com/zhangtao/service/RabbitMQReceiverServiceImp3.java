package com.zhangtao.service;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zhangtao.domain.AopMongoLog;
import com.zhangtao.util.RabbitMQUtil;
import com.zhangtao.util.Snowflake;
import com.zhangtao.util.SpringContextUtil;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtao on 2017/7/20.
 */
@Service("rabbitMQReceiverServiceImp3")
public final class RabbitMQReceiverServiceImp3 implements RabbitMQReceiverService {

    private ConnectionFactory getFirstConnectionFactory() {
        if (firstConnectionFactory == null)
            firstConnectionFactory = (ConnectionFactory) SpringContextUtil.getBean("firstConnectionFactory");
        return firstConnectionFactory;
    }

    private Queue getFirstQueue() {
        if (firstQueue == null)
            firstQueue = (Queue) SpringContextUtil.getBean("ex1Routing2Queue");
        return firstQueue;
    }

    @Autowired
    @Qualifier("firstConnectionFactory")
    private ConnectionFactory firstConnectionFactory;

    @Autowired
    @Qualifier("ex1Routing2Queue")
    private Queue firstQueue;

    @Bean(name = "firstmessageContainer3")
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public SimpleMessageListenerContainer firstmessageContainer() throws Exception {
        try {
            SimpleMessageListenerContainer container = RabbitMQUtil.getSimpleMessageListenerContainer(getFirstConnectionFactory(), getFirstQueue());
            container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
            container.setMessageListener(new ChannelAwareMessageListener() {

                @Override
                public void onMessage(Message message, Channel channel) throws Exception {
                    channel.basicQos(1, true);

                    if (onMessageEx(message, channel)) {
                        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
                    } else {
//                        channel.basicRecover(true);
                        channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//                        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
                    }
                }
            });
            return container;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Autowired
    private MongoService<AopMongoLog> mongoService;

    @Override
    public boolean onMessageEx(Message message, Channel channel) throws Exception {
        try {
            byte[] body = message.getBody();
            String mongojson = new String(body);
            String s1 = JSON.toJSONString(message);
            String s2 = JSON.toJSONString(channel);
            AopMongoLog aopMongoLog = JSON.parseObject(mongojson, AopMongoLog.class);
            aopMongoLog.setId(String.valueOf(Snowflake.nextId()));
            mongoService.mongo2save(aopMongoLog);
            System.out.println("rabbitMQReceiverServiceImp3成功消费 : " + mongojson);
            System.out.println("message : " + s1);
            System.out.println("channel : " + s2);
            return true;
        } catch (Exception ex) {
//            ex.printStackTrace();
            return false;
        }
    }

}
