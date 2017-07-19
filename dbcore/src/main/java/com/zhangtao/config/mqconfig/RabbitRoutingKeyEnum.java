package com.zhangtao.config.mqconfig;

/**
 * Created by zhangtao on 2017/7/18.
 */
public enum RabbitRoutingKeyEnum {

    routing1("routing1", "第一个路由"),
    routing2("routing2", "第二个路由");

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