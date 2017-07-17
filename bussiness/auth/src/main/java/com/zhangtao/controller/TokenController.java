package com.zhangtao.controller;

import com.alibaba.fastjson.JSON;
import com.zhangtao.domain.*;
import com.zhangtao.domain.user.UserDetails;
import com.zhangtao.encrypts.EncryHelper;
import com.zhangtao.service.MongoService;
import com.zhangtao.service.RedisService;
import com.zhangtao.service.RedisServiceImp;
import com.zhangtao.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangtao on 2017/7/9.
 */
@Controller
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MongoService<LogForMongo> logForMongoService;

    @ResponseBody
    @RequestMapping(value = "/md5", method = RequestMethod.GET)
    public String EncoderByMd5(String str) throws Exception {
        try {
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

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String Test(@RequestHeader HttpHeaders httpHeaders, String str) throws Exception {
        try {
            LogForMongo logForMongo1 = new LogForMongo();
            logForMongo1.setHeaders(httpHeaders);
            logForMongoService.mongo1save(logForMongo1);

            LogForMongo logForMongo3 = new LogForMongo();
            logForMongo3.setHeaders(httpHeaders);
            logForMongo3.setRequestParam(str);
            logForMongoService.mongo2save(logForMongo3);

            String s = JSON.toJSONString(logForMongo3);

            if (redisService.authhas(str)) {
                String ss = redisService.authget(str).toString();
                LogForMongo logForMongo=JSON.parseObject(ss,LogForMongo.class);
                System.out.println(JSON.toJSONString(logForMongo));
            } else {
                redisService.authset(str, s, 10, TimeUnit.SECONDS);
            }
            return s;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestHeader HttpHeaders httpHeaders, @RequestBody Login login) throws Exception {
        ResponseEx responseEx = new ResponseEx();
        try {
            if (redisService.authhas(login.param)) {
                responseEx.setCode(ResCode.reprequest.getCode());
            } else {
                redisService.authset(login.param, login.param, 10, TimeUnit.SECONDS);
                String key = EncryHelper.RSApriD(login.key);
                String iv = EncryHelper.RSApriD(login.iv);
                String userJson = EncryHelper.AESD(login.param, key, iv);
                login.userLogin = JSON.parseObject(userJson, Login.UserLogin.class);
                long now = System.currentTimeMillis();
                if (now - login.userLogin.ts > 1000 * 60 * 5) {
                    responseEx.setCode(ResCode.expirets.getCode());
                } else {
                    boolean clientencry = EncryHelper.BcryptCheck(login.userLogin.pwd, login.userLogin.enpwd);
                    if (!clientencry) {
                        responseEx.setCode(ResCode.encryerr.getCode());
                    } else {
                        String md5 = EncoderByMd5(login.userLogin.account + login.userLogin.pwd + login.userLogin.enpwd + login.userLogin.ts);
                        boolean sign = md5.equals(login.userLogin.sign);
                        if (!sign) {
                            responseEx.setCode(ResCode.signerr.getCode());
                        } else {
                            UserDetails userDetails = userService.getUserInfoBeforeLogin(login.userLogin.account);
                            if (userDetails == null) {
                                responseEx.setCode(ResCode.accounterr.getCode());
                            } else {
                                boolean checkpwd = EncryHelper.BcryptCheck(login.userLogin.pwd, userDetails.getdPassword());
                                if (!checkpwd) {
                                    responseEx.setCode(ResCode.pwderr.getCode());
                                } else {
                                    String tokenAESkey = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
                                    UserInfoForTokenInRedis userInfoForTokenInRedis = new UserInfoForTokenInRedis();
                                    userInfoForTokenInRedis.setdAccount(userDetails.getdAccount());
                                    userInfoForTokenInRedis.setdId(userDetails.getdId());
                                    userInfoForTokenInRedis.setdMid(userDetails.getdMid());
                                    userInfoForTokenInRedis.setExpire(now + 1000 * 60 * 60 * 24 * 7);
                                    userInfoForTokenInRedis.setKey(key);
                                    userInfoForTokenInRedis.setIv(iv);
                                    userInfoForTokenInRedis.setTokenAESkey(tokenAESkey);

                                    TokenForResponse tokenForResponse = new TokenForResponse();
                                    tokenForResponse.setAccount(userDetails.getdAccount());
                                    tokenForResponse.setExpire(now + 1000 * 60 * 60 * 24 * 7);
                                    String tokenJson = JSON.toJSONString(tokenForResponse);
                                    String entoken = EncryHelper.AESE(tokenJson, tokenAESkey).replace("/", "_");
                                    System.out.println(entoken);
                                    responseEx.setCode(ResCode.success.getCode());
                                    String responsToken = EncryHelper.AESE(entoken, key, iv);
                                    responseEx.setToken(responsToken);

                                    redisService.authset(responsToken, JSON.toJSONString(userInfoForTokenInRedis), 10, TimeUnit.SECONDS);
                                }
                            }
                        }
                    }
                }
            }
            return JSON.toJSONString(responseEx);
        } catch (Exception ex) {
            ex.printStackTrace();
            responseEx.setCode(ResCode.fail.getCode());
            return JSON.toJSONString(responseEx);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/checkget", method = {RequestMethod.GET})
    public boolean checkGet(@RequestHeader HttpHeaders httpHeaders, @RequestParam String param) {
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/checkPost", method = {RequestMethod.POST})
    public String checkPost(@RequestHeader HttpHeaders httpHeaders, @RequestBody RequestEx requestEx) {
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        requestEx.token = "ok";
        boolean b = EncryHelper.BcryptCheck("27ae1e5602f2703dcda4abb1177e7cca31ce81df7c939e5a6d30044ae66a719331e5b73bacee5bd8771eeb1729b3661e1f8ae83e1025e19189813887a90c7bba", requestEx.param);
        System.out.println(b);
        String s = JSON.toJSONString(requestEx);
        return s;
    }

    @ResponseBody
    @RequestMapping(value = "/checkPut", method = {RequestMethod.PUT})
    public boolean checkPut(@RequestHeader HttpHeaders httpHeaders, @RequestBody RequestEx requestEx) {
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/checkDel", method = {RequestMethod.DELETE})
    public boolean checkDel(@RequestHeader HttpHeaders httpHeaders, @RequestBody RequestEx requestEx) {
        return true;
    }

}
