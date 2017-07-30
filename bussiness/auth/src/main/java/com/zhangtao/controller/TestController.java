package com.zhangtao.controller;

import com.alibaba.fastjson.JSON;
import com.zhangtao.common.Fel;
import com.zhangtao.domain.user.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangtao on 2017/7/30.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void findById(@PathVariable("id") int id) {
        Hashtable<String, Integer> hashtable = Fel.getSingleton().GetResult(id);
        for (Map.Entry entry : hashtable.entrySet()) {
            System.out.println(entry.getKey().toString());
        }
        System.out.println(hashtable.size());
    }
}
