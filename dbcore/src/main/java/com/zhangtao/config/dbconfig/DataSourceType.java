package com.zhangtao.config.dbconfig;

public enum DataSourceType {

    userDBread("userDBread", "用户库读库"),
    userDBwrite("userDBwrite", "用户库写库"),
    goodsDBread("goodsDBread", "货源库读库"),
    goodsDBwrite("goodsDBwrite", "货源库写库"),
    quotesDBread("quotesDBread", "报价库读库"),
    quotesDBwrite("quotesDBwrite", "报价库写库"),
    financeDBread("financeDBread", "财务库读库"),
    financeDBwrite("financeDBwrite", "财务库写库"),
    queueDBread("queueDBread", "队列库读库"),
    queueDBwrite("queueDBwrite", "队列库写库");

    private String type;

    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
