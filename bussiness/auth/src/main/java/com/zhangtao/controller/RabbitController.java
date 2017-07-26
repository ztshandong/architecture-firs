package com.zhangtao.controller;

import com.alibaba.fastjson.JSON;
import com.zhangtao.domain.AopMongoLog;
import com.zhangtao.service.RabbitMQSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * Created by zhangtao on 2017/7/18.
 */
@Controller
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    @Resource(name = "rabbitMQSenderServiceImp1")
    RabbitMQSenderService senderService1;

    @Autowired
    @Resource(name = "rabbitMQSenderServiceImp2")
    RabbitMQSenderService senderService2;
    @Autowired
    @Resource(name = "rabbitMQSenderServiceImp3")
    RabbitMQSenderService senderService3;
    //    @Autowired
//    private Sender sender;
//
    @ResponseBody
    @RequestMapping(value = "/{str}/{num}", method = RequestMethod.GET)
    public String test(@PathVariable("str") String str, @PathVariable("num") Integer num) throws Exception {
        try {
            String s1 = UUID.randomUUID().toString();
            String s2 = UUID.randomUUID().toString();
            System.out.println("s1:" + s1);
            System.out.println("s2:" + s2);
//            senderService.send1("队列1：" + str, s1);
            AopMongoLog aopMongoLog = new AopMongoLog();
            aopMongoLog.setSql(str);
            for (int i = 0; i < num; i++) {
//                senderService2.send(str, s2);
//                System.out.println("senderService1================");
//                senderService1.send(JSON.toJSONString(aopMongoLog), s2);
//                System.out.println("senderService2================");
//                senderService2.send(JSON.toJSONString(aopMongoLog), s2);
                System.out.println("senderService================");
                senderService1.send(JSON.toJSONString(aopMongoLog), s2);
            }
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
