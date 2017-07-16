package com.zhangtao.config.dbconfig;

import javax.sql.DataSource;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库源配置
 *
 * @author 张涛
 */
@Configuration
public class DataSourceConfiguration {

    private static Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${mysql.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "userDBWriteDataSource")
    @Primary
    @ConfigurationProperties(prefix = "mysql.datasource.userdbwrite")
    public DataSource userDBWriteDataSource() {
        log.info("-------------------- userDBWriteDataSource init ---------------------");
        return initDataSource();
    }

    @Bean(name = "userDBReadDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.userdbread")
    public DataSource userDBReadDataSource() {
        log.info("-------------------- userDBReadDataSource init ---------------------");
//        return DataSourceBuilder.create().type(dataSourceType).build();
        return initDataSource();
    }

    @Bean(name = "goodsDBWriteDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.goodsdbwrite")
    public DataSource goodsDBWriteDataSource() {
        log.info("-------------------- goodsDBWriteDataSource init ---------------------");
        return initDataSource();
    }

    @Bean(name = "goodsDBReadDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.goodsdbread")
    public DataSource goodsDBReadDataSource() {
        log.info("-------------------- goodsDBReadDataSource init ---------------------");
        return initDataSource();
    }

    @Bean(name = "quotesDBWriteDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.quotesdbwrite")
    public DataSource quotesDBWriteDataSource() {
        log.info("-------------------- quotesDBWriteDataSource init ---------------------");
        return initDataSource();
    }

    @Bean(name = "quotesDBReadDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.quotesdbread")
    public DataSource quotesDBReadDataSource() {
        log.info("-------------------- quotesDBReadDataSource init ---------------------");
        return initDataSource();
    }

    @Bean(name = "financeDBWriteDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.financedbwrite")
    public DataSource financeDBWriteDataSource() {
        log.info("-------------------- financeDBWriteDataSource init ---------------------");
        return initDataSource();
    }

    @Bean(name = "financeDBReadDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.financedbread")
    public DataSource financeDBReadDataSource() {
        log.info("-------------------- financeDBReadDataSource init ---------------------");
        return initDataSource();
    }
    @Bean(name = "queueDBWriteDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.queuedbwrite")
    public DataSource queueDBWriteDataSource() {
        log.info("-------------------- queueDBWriteDataSource init ---------------------");
        return initDataSource();
    }

    @Bean(name = "queueDBReadDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.queuedbread")
    public DataSource queueDBReadDataSource() {
        log.info("-------------------- queueDBReadDataSource init ---------------------");
        return initDataSource();
    }
//    @Bean(name = "readDataSource01")
//    @ConfigurationProperties(prefix = "mysql.userdbdatasource.read01")
//    public DataSource readDataSourceOne() {
//        log.info("-------------------- read01 DataSourceOne init ---------------------");
////        return DataSourceBuilder.create().type(dataSourceType).build();
//        return  initDataSource();
//    }
//
//    @Bean(name = "readDataSource02")
//    @ConfigurationProperties(prefix = "mysql.userdbdatasource.read02")
//    public DataSource readDataSourceTwo() {
//        log.info("-------------------- read02 DataSourceTwo init ---------------------");
////        return DataSourceBuilder.create().type(dataSourceType).build();
//        return  initDataSource();
//    }
    private DruidDataSource initDataSource() {
        try {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource = (DruidDataSource) DataSourceBuilder.create().type(dataSourceType).build();
            List<Filter> filterList = new ArrayList<Filter>();
            filterList.add(wallFilter());
            dataSource.setProxyFilters(filterList);
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig());
        return wallFilter;
    }

    @Bean
    public WallConfig wallConfig() {
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);//允许一次执行多条语句
        config.setNoneBaseStatementAllow(true);//允许非基本语句的其他语句
        return config;
    }
}
