//package com.zhangtao.dao;
//
//import com.zhangtao.common.Snowflake;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// * Created by zhangtao on 2017/7/3.
// */
//@FeignClient("commontools")
//public interface CommonTools {
//    @RequestMapping("/commontools/getid/")
//    long getid();
//
//    @RequestMapping("/commontools/sha512")
//    String sha512(@RequestParam(value = "text") String text);
//
//    @RequestMapping("/commontools/encodeHex")
//    String encodeHex(@RequestParam(value = "text") String text);
//
//    @RequestMapping("/commontools/decodeHex")
//    String decodeHex(@RequestParam(value = "text") String text);
//
//    @RequestMapping("/commontools/encodeBase64")
//    String encodeBase64(@RequestParam(value = "text") byte[] text);
//
//    @RequestMapping("/commontools/decodeBase64")
//    byte[] decodeBase64(@RequestParam(value = "text") String text);
//
//}
