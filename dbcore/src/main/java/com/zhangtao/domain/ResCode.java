package com.zhangtao.domain;

/**
 * Created by zhangtao on 2017/7/12.
 */
public enum  ResCode {
    reprequest("1", "重放"),
    expirets("2", "过期"),
    signerr("3", "md5校验失败"),
    encryerr("4", "前端加密错误"),
    accounterr("5", "无效用户"),
    pwderr("6", "密码错误"),
    success("0", "成功"),
    fail("-1", "失败");

    private String code;

    private String name;

    ResCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
