package com.zhangtao.config;

/**
 * Created by zhangtao on 2017/7/18.
 */
public enum  RabbitQueueEnum {

    queue1("hello1", "第一个队列"),
    queue2("hello2", "第二个队列");

    private String type;

    private String name;

    RabbitQueueEnum(String type, String name) {
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
