package com.zhangtao.domain;

import java.io.Serializable;

/**
 * Created by zhangtao on 2017/7/12.
 */
public class UserInfoForTokenInRedis {
    private long dId;
    private long dMid;
    private String dAccount;
    private long expire;
    private String tokenAESkey;
    private String key;
    private String iv;

    public void setdId(long dId) {
        this.dId = dId;
    }

    public void setdMid(long dMid) {
        this.dMid = dMid;
    }

    public void setdAccount(String dAccount) {
        this.dAccount = dAccount;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public void setTokenAESkey(String tokenAESkey) {
        this.tokenAESkey = tokenAESkey;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public long getdId() {
        return dId;
    }

    public long getdMid() {
        return dMid;
    }

    public String getdAccount() {
        return dAccount;
    }

    public long getExpire() {
        return expire;
    }

    public String getTokenAESkey() {
        return tokenAESkey;
    }

    public String getKey() {
        return key;
    }

    public String getIv() {
        return iv;
    }

}
