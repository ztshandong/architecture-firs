package com.zhangtao.service;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zhangtao.domain.AopMongoLog;
import com.zhangtao.util.RabbitMQUtil;
import com.zhangtao.util.Snowflake;
import com.zhangtao.util.SpringContextUtil;
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
@Service("rabbitMQReceiverServiceImp2")
public final class RabbitMQReceiverServiceImp2 implements RabbitMQReceiverService {

    private ConnectionFactory getSecondConnectionFactory() {
        if (secondConnectionFactory == null)
            secondConnectionFactory = (ConnectionFactory) SpringContextUtil.getBean("secondConnectionFactory");
        return secondConnectionFactory;
    }

    private Queue getSecondQueue() {
        if (secondQueue == null)
            secondQueue = (Queue) SpringContextUtil.getBean("ex2Routing2Queue");
        return secondQueue;
    }

    @Autowired
    @Qualifier("secondConnectionFactory")
    private ConnectionFactory secondConnectionFactory;

    @Autowired
    @Qualifier("ex2Routing2Queue")
    private Queue secondQueue;

    @Bean(name = "secondmessageContainer2")
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public SimpleMessageListenerContainer secondmessageContainer() throws Exception {
        try {
            SimpleMessageListenerContainer container = RabbitMQUtil.getSimpleMessageListenerContainer(getSecondConnectionFactory(), getSecondQueue());
            container.setMessageListener(new ChannelAwareMessageListener() {

                @Override
                public void onMessage(Message message, Channel channel) throws Exception {
                    channel.basicQos(1);
                    if (onMessageEx(message, channel)) {
                        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
                    } else {
                        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
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
            System.out.println("rabbitMQReceiverServiceImp2成功消费 : " + mongojson);
            System.out.println("message : " + s1);
            System.out.println("channel : " + s2);
            return true;
        } catch (Exception ex) {
//            ex.printStackTrace();
            return false;
        }
    }

//
//    @Override
//    public boolean onMessageEx(Message message, Channel channel) throws Exception {
//        return false;
//    }
}
