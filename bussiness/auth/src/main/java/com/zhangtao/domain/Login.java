package com.zhangtao.domain;

/**
 * Created by zhangtao on 2017/7/11.
 */
public class Login {
    public String param;
    public String key;
    public String iv;
    public long ts;
    public UserLogin userLogin;

    public static class UserLogin {
        public String account;
        public String pwd;
        public String enpwd;
        public long ts;
        public String sign;
    }

}
