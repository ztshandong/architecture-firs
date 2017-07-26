package com.zhangtao.config.mqconfig;

/**
 * Created by zhangtao on 2017/7/18.
 */
public enum RabbitRoutingKeyEnum {

    ex1Routing1("ex1Routing1", "第一个交换机第一个路由"),
    ex1Routing2("ex1Routing2", "第一个交换机第二个路由"),
    ex2Routing1("ex2Routing1", "第二个交换机第一个路由"),
    ex2Routing2("ex2Routing2", "第二个交换机第二个路由");

    private String type;

    private String name;

    RabbitRoutingKeyEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

//    public void setType(String type) {
//        this.type = type;
//    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
}