package com.zhangtao.controller;

import com.alibaba.fastjson.JSON;
import com.zhangtao.domain.user.UserDetails;
import com.zhangtao.encrypts.EncryHelper;
import com.zhangtao.service.RedisServiceImp;
import com.zhangtao.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangtao on 2017/7/3.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisServiceImp redisServiceImp;

    @RequestMapping(value = "/beforeLogin/", method = RequestMethod.POST)
    @ResponseBody
    public String beforeLogin(@RequestParam(value = "key", required = true, defaultValue = "") String key) throws Exception {
        try {
            String u = "";
            if (key.equals("")) {
                System.out.println("key为空");
                return u;
            }
            String decodeStr = EncryHelper.deHEX(key);
            String daccountstr = EncryHelper.RSApriD(decodeStr);
            UserDetails userDetails = JSON.toJavaObject(JSON.parseObject(daccountstr), UserDetails.class);
            String daccount = userDetails.getdAccount();
            UserDetails usalt = userService.getUserInfoBeforeLogin(daccount);
            if (usalt != null) {
                usalt.setdAccount("hello");
                u = JSON.toJSONString(usalt);
            }
            return u;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public String login(@RequestBody Login login) throws Exception {
//
//        Login login1 = new Login();
//        login1.key = EncryHelper.deHEX(login.key);
//        login1.lock = EncryHelper.deHEX(login.lock);
//
//        String KEY = EncryHelper.RSApriD(login1.key);
//        String decrypt = EncryHelper.AESD(login1.lock, KEY);
//        System.out.println("解密后：" + decrypt);
//
//        return decrypt;
//    }

//    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    //@RequestParam(value="name", required=true, defaultValue="0")
//    public String findById(@PathVariable("id") Long id) {
//        System.currentTimeMillis();
//        IniRedis();
//        if (authRedis.hasKey(Long.toString(id))) {
//
//            System.out.println("get from redis");
//            return authops.get(Long.toString(id));
//        }
//        UserDetails userDetails = userService.findById(id);
//        String value = JSON.toJSONString(userDetails);
//        authops.set(Long.toString(id), value, 10, TimeUnit.SECONDS);
//        return value;
//    }
}


