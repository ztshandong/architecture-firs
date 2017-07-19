package com.zhangtao.controller;

import com.zhangtao.service.RabbitMQSenderService;
import com.zhangtao.service.SenderService;
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
    @Resource
    SenderService senderService;

    //    @Autowired
//    private Sender sender;
//
    @ResponseBody
    @RequestMapping(value = "/{str}", method = RequestMethod.GET)
    public String test(@PathVariable("str") String str) throws Exception {
        try {
            String s1 = UUID.randomUUID().toString();
            String s2 = UUID.randomUUID().toString();
            System.out.println("s1:" + s1);
            System.out.println("s2:" + s2);
            senderService.send1("队列1：" + str, s1);
            senderService.send2("队列2：" + str, s2);
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
