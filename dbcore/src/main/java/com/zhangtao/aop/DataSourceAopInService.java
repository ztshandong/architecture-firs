package com.zhangtao.aop;

import com.zhangtao.config.dbconfig.DataSourceContextHolder;
import com.zhangtao.config.dbconfig.DataSourceType;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * 在service层觉得数据源
 * <p>
 * 必须在事务AOP之前执行，所以实现Ordered,order的值越小，越先执行
 * 如果一旦开始切换到写库，则之后的读都会走写库
 *
 * @author Jfei
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Component
//@Order(LOWEST_PRECEDENCE)
public class DataSourceAopInService implements PriorityOrdered {

    private static Logger log = LoggerFactory.getLogger(DataSourceAopInService.class);

    /*
    @Before("execution(* com.zhangtao.service..*.find*(..)) "
            + " || execution(* com.zhangtao.service..*.get*(..)) "
            + " || execution(* com.zhangtao.service..*.query*(..))")
    public void setUserDBReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读
        if(!DataSourceType.userDBwrite.getType().equals(DataSourceContextHolder.getReadOrWrite())){
            DataSourceContextHolder.setUserDBRead();
        }

    }

    @Before("execution(* com.zhangtao.service..*.insert*(..)) "
            + " || execution(* com.zhangtao.service..*.update*(..))"
            + " || execution(* com.zhangtao.service..*.add*(..))")
    public void setUserDBWriteDataSourceType() {
        DataSourceContextHolder.setUserDBWrite();
    }
    */

     /*
    @Pointcut("execution(* com.zhangtao.dao..*.*(..)) ")
    public void sqllog() {
    }

    @Around("sqllog()")
    public Object addOperateLog(ProceedingJoinPoint point) throws Throwable {
        Object[] params = point.getArgs();// 获取参数
        String  kind= point.getKind();
        String methodName = point.getSignature().getName();// 获取方法名
        String declaringTypeName = point.getSignature().getDeclaringTypeName();// 获取方法名
        Signature signature = point.getSignature();// 获取方法名

        Class<?> targetClass = point.getTarget().getClass();// 获取目标对象的类名
        System.out.println("aroundsqllog:"+JSON.toJSONString(params));
        System.out.println(JSON.toJSONString(signature));
        System.out.println(JSON.toJSONString(methodName));
        System.out.println(JSON.toJSONString(kind));
        System.out.println(JSON.toJSONString(declaringTypeName));
        System.out.println(JSON.toJSONString(targetClass));
        rabbitMQSenderService.send1(JSON.toJSONString(methodName), UUID.randomUUID().toString());
      AopMongoLog aopMongoLog=new AopMongoLog();
      aopMongoLog.setSql(JSON.toJSONString(methodName));
       logForMongoService.mongo1save(aopMongoLog);
        return point.proceed();
    }*/

    @Before("execution(* com.zhangtao.service..*.*(..)) "
            + " && @annotation(com.zhangtao.annotation.UserDBWriteDataSource) ")
    public void setUserDBWriteDataSourceType() {
        DataSourceContextHolder.setUserDBWrite();
    }

    @Before("execution(* com.zhangtao.service..*.*(..)) "
            + " && @annotation(com.zhangtao.annotation.UserDBReadDataSource) ")
    public void setUserDBReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读
        if (!DataSourceType.userDBwrite.getType().equals(DataSourceContextHolder.getReadOrWrite())) {
            DataSourceContextHolder.setUserDBRead();
        }
    }

    @Before("execution(* com.zhangtao.service..*.*(..)) "
            + " && @annotation(com.zhangtao.annotation.GoodsDBWriteDataSource) ")
    public void setGoodsDBWriteDataSourceType() {
        DataSourceContextHolder.setGoodsDBWrite();
    }

    @Before("execution(* com.zhangtao.service..*.*(..)) "
            + " && @annotation(com.zhangtao.annotation.GoodsDBReadDataSource) ")
    public void setGoodsDBReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读
        if (!DataSourceType.goodsDBwrite.getType().equals(DataSourceContextHolder.getReadOrWrite())) {
            DataSourceContextHolder.setGoodsDBRead();
        }
    }

    @Before("execution(* com.zhangtao.service..*.*(..)) "
            + " && @annotation(com.zhangtao.annotation.QuotesDBWriteDataSource) ")
    public void setQuotesDBWriteDataSourceType() {
        DataSourceContextHolder.setQuotesDBWrite();
    }

    @Before("execution(* com.zhangtao.service..*.*(..)) "
            + " && @annotation(com.zhangtao.annotation.QuotesDBReadDataSource) ")
    public void setQuotesDBReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读
        if (!DataSourceType.quotesDBwrite.getType().equals(DataSourceContextHolder.getReadOrWrite())) {
            DataSourceContextHolder.setQuotesDBRead();
        }
    }

    @Before("execution(* com.zhangtao.service..*.*(..)) "
            + " && @annotation(com.zhangtao.annotation.FinanceDBWriteDataSource) ")
    public void setFinanceDBWriteDataSourceType() {
        DataSourceContextHolder.setFinanceDBWrite();
    }

    @Before("execution(* com.zhangtao.service..*.*(..)) "
            + " && @annotation(com.zhangtao.annotation.FinanceDBReadDataSource) ")
    public void setFinanceDBReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读
        if (!DataSourceType.financeDBwrite.getType().equals(DataSourceContextHolder.getReadOrWrite())) {
            DataSourceContextHolder.setFinanceDBRead();
        }
    }

    @Before("execution(* com.zhangtao.service..*.*(..)) "
            + " && @annotation(com.zhangtao.annotation.QueueDBWriteDataSource) ")
    public void setQueueDBWriteDataSourceType() {
        DataSourceContextHolder.setQueueDBWrite();
    }

    @Before("execution(* com.zhangtao.service..*.*(..)) "
            + " && @annotation(com.zhangtao.annotation.QueueDBReadDataSource) ")
    public void setQueueDBReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读
        if (!DataSourceType.queueDBwrite.getType().equals(DataSourceContextHolder.getReadOrWrite())) {
            DataSourceContextHolder.setQueueDBRead();
        }
    }

    @Override
    public int getOrder() {

        return 1;
//		return 2147483646;
//		return HIGHEST_PRECEDENCE;
    }

}
