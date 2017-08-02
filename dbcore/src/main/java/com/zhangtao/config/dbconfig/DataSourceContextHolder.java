package com.zhangtao.config.dbconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本地线程，数据源上下文
 *
 * @author 张涛
 */
public final class DataSourceContextHolder {

    private static Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

    //线程本地环境
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    private static final String DB_CHANGE_INFO = "数据库切换到";

    /**
     * 读库
     */
    public static void setUserDBRead() {
        local.set(DataSourceType.userDBread.getType());
        log.info(DB_CHANGE_INFO + DataSourceType.userDBread.getName());
    }

    /**
     * 写库
     */
    public static void setUserDBWrite() {
        local.set(DataSourceType.userDBwrite.getType());
        log.info(DB_CHANGE_INFO + DataSourceType.userDBwrite.getName());
    }

    /**
     * 读库
     */
    public static void setaliUserDBRead() {
        local.set(DataSourceType.aliuserDBread.getType());
        log.info(DB_CHANGE_INFO + DataSourceType.aliuserDBread.getName());
    }

    /**
     * 写库
     */
    public static void setaliUserDBWrite() {
        local.set(DataSourceType.aliuserDBwrite.getType());
        log.info(DB_CHANGE_INFO + DataSourceType.aliuserDBwrite.getName());
    }

    /**
     * 读库
     */
    public static void setGoodsDBRead() {
        local.set(DataSourceType.goodsDBread.getType());
        log.info(DB_CHANGE_INFO + DataSourceType.goodsDBread.getName());
    }

    /**
     * 写库
     */
    public static void setGoodsDBWrite() {
        local.set(DataSourceType.goodsDBwrite.getType());
        log.info(DB_CHANGE_INFO + DataSourceType.goodsDBwrite.getName());
    }

    /**
     * 读库
     */
    public static void setQuotesDBRead() {
        local.set(DataSourceType.quotesDBread.getType());
        log.info(DB_CHANGE_INFO + DataSourceType.quotesDBread.getName());
    }

    /**
     * 写库
     */
    public static void setQuotesDBWrite() {
        local.set(DataSourceType.quotesDBwrite.getType());
        log.info(DB_CHANGE_INFO + DataSourceType.quotesDBwrite.getName());
    }

    /**
     * 读库
     */
    public static void setFinanceDBRead() {
        local.set(DataSourceType.financeDBread.getType());
        log.info(DB_CHANGE_INFO + DataSourceType.financeDBread.getName());
    }

    /**
     * 写库
     */
    public static void setFinanceDBWrite() {
        local.set(DataSourceType.financeDBwrite.getType());
        log.info(DB_CHANGE_INFO + DataSourceType.financeDBwrite.getName());
    }
    /**
     * 读库
     */
    public static void setQueueDBRead() {
        local.set(DataSourceType.queueDBread.getType());
        log.info(DB_CHANGE_INFO + DataSourceType.queueDBread.getName());
    }

    /**
     * 写库
     */
    public static void setQueueDBWrite() {
        local.set(DataSourceType.queueDBwrite.getType());
        log.info(DB_CHANGE_INFO + DataSourceType.queueDBwrite.getName());
    }
    public static String getReadOrWrite() {
        return local.get();
    }

    public static void clear() {
        local.remove();
    }
}
