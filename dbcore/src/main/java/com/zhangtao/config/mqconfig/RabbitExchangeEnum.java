package com.zhangtao.config.mqconfig;

/**
 * Created by zhangtao on 2017/7/19.
 */
public enum RabbitExchangeEnum {

    exchange1("exchange1", "第一个交换机"),
    exchange2("exchange2", "第二个交换机");

    private String type;

    private String name;

    RabbitExchangeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

}
