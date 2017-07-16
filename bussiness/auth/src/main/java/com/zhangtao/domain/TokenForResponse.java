package com.zhangtao.domain;

/**
 * Created by zhangtao on 2017/7/12.
 */
public class TokenForResponse {

    private String account;
    private long expire;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

}
