package com.zhangtao.domain;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;

/**
 * Created by zhangtao on 2017/7/16.
 */
public class LogForMongo {
    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public RequestEx getRequestEx() {
        return requestEx;
    }

    public void setRequestEx(RequestEx requestEx) {
        this.requestEx = requestEx;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    HttpHeaders headers;
    RequestEx requestEx;
    String requestParam;
    Login login;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
