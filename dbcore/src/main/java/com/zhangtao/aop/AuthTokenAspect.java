package com.zhangtao.aop;

import com.alibaba.fastjson.JSON;
import com.zhangtao.domain.AopMongoLog;
import com.zhangtao.domain.PostBody;
import com.zhangtao.domain.ResCode;
import com.zhangtao.service.MongoService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangtao on 2017/7/20.
 */
@Aspect
@Component
@Order(1)
public class AuthTokenAspect {

    @Autowired
    private MongoService<AopMongoLog> logForMongoService;

    @Pointcut("@annotation(com.zhangtao.annotation.AuthToken)")
    public void authToken() {
    }

    // 定义 advise，必须用Around，否则验证不通过只能靠抛异常
    @Around("authToken()")
    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            AopMongoLog aopMongoLog = getAopMongoLog(joinPoint);

            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg == null) continue;
                if (arg.getClass() == PostBody.class) {
                    aopMongoLog.setPostBody((PostBody) arg);
                    break;
                }
            }

            if (checkUserToken(aopMongoLog))
                return joinPoint.proceed();
            else
                return JSON.toJSONString(ResCode.fail.getCode());
        } catch (Exception ex) {
            ex.printStackTrace();
            return JSON.toJSONString(ResCode.fail.getCode());
        }
    }

    private AopMongoLog getAopMongoLog(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        AopMongoLog aopMongoLog = new AopMongoLog();
        StringBuffer url = request.getRequestURL();
        if (request.getQueryString() != null) {
            url.append("?");
            url.append(request.getQueryString());
        }
        aopMongoLog.setRequestUrl(url.toString());
        aopMongoLog.setRemoteIp(getIpAddr(request));
        aopMongoLog.setSign(request.getHeader("sign"));
        aopMongoLog.setToken(request.getHeader("token"));
        aopMongoLog.setTs(Long.parseLong(request.getHeader("ts")));
        aopMongoLog.setRequestMethod(request.getMethod());
        String controllerMethod = joinPoint.getSignature().getDeclaringTypeName() + '.' + joinPoint.getSignature().getName();
        aopMongoLog.setControllerMethod(controllerMethod);
        return aopMongoLog;
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private boolean checkUserToken(AopMongoLog aopMongoLog) {
//    private String checkUserToken(HttpServletRequest request) {
        aopMongoLog.setResCode(ResCode.success);
        logForMongoService.mongo1save(aopMongoLog);
        System.out.println(JSON.toJSONString(aopMongoLog));

        return true;
//        Cookie[] cookies = request.getCookies();
//        if (cookies == null) {
//            return "";
//        }
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equalsIgnoreCase("user_token")) {
//                return cookie.getValue();
//            }
//        }
//        return "";
    }

//    //pjp参数由spring注入
//    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
//        MethodSignature ms = (MethodSignature) pjp.getSignature();
//        //获取当前正在执行的方法
//        Method method = ms.getMethod();
//        //判断该方法是否被注解@Token标记
//        if (method.isAnnotationPresent(AuthToken.class)) {
//            //执行token验证
//        }
//
//        //继续执行方法
//        return pjp.proceed();
//    }
}
