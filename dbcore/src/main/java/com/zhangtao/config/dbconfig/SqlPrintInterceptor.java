package com.zhangtao.config.dbconfig;

import com.alibaba.fastjson.JSON;
import com.zhangtao.domain.AopMongoLog;
import com.zhangtao.service.MongoService;
import com.zhangtao.service.MongoServiceImp;
import com.zhangtao.service.RabbitMQSenderService;
import org.apache.catalina.core.ApplicationContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;

/**
 * MyBatis 将mybatis要执行的sql拦截打印出来
 *
 * @since 1.0.0
 */
@Intercepts
        ({
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
        })
public class SqlPrintInterceptor implements Interceptor {

//    @Autowired
//    MongoService<String> mongoService;
    MongoService<AopMongoLog> mongoService=new MongoServiceImp<AopMongoLog>();

    private static Log logger = LogFactory.getLog(SqlPrintInterceptor.class);

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {

            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            Object parameterObject = null;
            if (invocation.getArgs().length > 1) {
                parameterObject = invocation.getArgs()[1];
            }

            long start = System.currentTimeMillis();

            Object result = invocation.proceed();

            String statementId = mappedStatement.getId();
            BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
            Configuration configuration = mappedStatement.getConfiguration();


            long end = System.currentTimeMillis();
            long timing = end - start;
            String sql = "执行sql耗时:" + timing + " ms" + " - id:" + statementId + " - Sql:" + getSql(boundSql, parameterObject, configuration);
//        String sql = "执行sql耗时:" + timing + " ms" + " - id:" + statementId + " - Sql:" + getSql(boundSql, parameterObject, configuration);
            logger.info("   " + sql);
            logger.info("mongoService:" + mongoService);
            AopMongoLog aopMongoLog=new AopMongoLog();
            aopMongoLog.setSql(sql);
            if (mongoService != null)
                mongoService.mongo1save(aopMongoLog);
            //        if(logger.isInfoEnabled()){
//            logger.info("执行sql耗时:" + timing + " ms" + " - id:" + statementId + " - Sql:" );
//            logger.info("   "+sql);
//        }

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "执行sql失败";
        }
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private String getSql(BoundSql boundSql, Object parameterObject, Configuration configuration) {
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if (parameterMappings != null) {
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(parameterObject);
                        value = metaObject.getValue(propertyName);
                    }
                    sql = replacePlaceholder(sql, value);
                }
            }
        }
        return sql;
    }

    private String replacePlaceholder(String sql, Object propertyValue) {
        String result;
        if (propertyValue != null) {
            if (propertyValue instanceof String) {
                result = "'" + propertyValue + "'";
            } else if (propertyValue instanceof Date) {
                result = "'" + DATE_FORMAT.format(propertyValue) + "'";
            } else {
                result = propertyValue.toString();
            }
        } else {
            result = "null";
        }
        return sql.replaceFirst("\\?", Matcher.quoteReplacement(result));
    }
}
