package com.zhangtao.domain;

/**
 * Created by zhangtao on 2017/7/21.
 */
public final class AopMongoLog {

    long ts;
    String token;
    String sign;
    String remoteIp;
    String requestUrl;
    String requestMethod;
    String controllerMethod;
    ResCode resCode;
    PostBody postBody;
    String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }


    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getControllerMethod() {
        return controllerMethod;
    }

    public void setControllerMethod(String controllerMethod) {
        this.controllerMethod = controllerMethod;
    }

    public PostBody getPostBody() {
        return postBody;
    }

    public void setPostBody(PostBody postBody) {
        this.postBody = postBody;
    }

    public ResCode getResCode() {
        return resCode;
    }

    public void setResCode(ResCode resCode) {
        this.resCode = resCode;
    }

}
