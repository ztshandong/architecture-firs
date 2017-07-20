package com.zhangtao.aop;

import com.alibaba.fastjson.JSON;
import com.zhangtao.annotation.AuthToken;
import com.zhangtao.config.dbconfig.DataSourceContextHolder;
import com.zhangtao.domain.LogForMongo;
import com.zhangtao.domain.RequestEx;
import com.zhangtao.service.MongoService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;


/**
 * Created by zhangtao on 2017/7/20.
 */
@Aspect
@Component
public class AuthTokenAspect {

    @Autowired
    private MongoService<RequestEx> logForMongoService;

    // 定义一个 Pointcut, 使用 切点表达式函数 来描述对哪些 Join point 使用 advise.
    @Pointcut("@annotation(com.zhangtao.annotation.AuthToken)")
//    @Before("execution(* com.zhangtao.controller..*.*(..)) "
//            + " && @annotation(com.zhangtao.annotation.AuthToken) ")
    public void pointcut() {
    }

    // 定义 advise
    @Around("pointcut()")
    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();

            // 检查用户所传递的 token 是否合法
            String token = getUserToken(request);
//            if (!token.equalsIgnoreCase("123456")) {
//                return "错误, 权限不合法!";
//            }

            return joinPoint.proceed();
        }
        catch (Exception ex){
            ex.printStackTrace();;
            return "无权查看";
        }
    }

    private String getUserToken(HttpServletRequest request) {
        RequestEx requestEx = new RequestEx();
        requestEx.token=request.getParameter("token");
        requestEx.param=request.getParameter("param");
        requestEx.sign=request.getParameter("sign");
        String ts=request.getParameter("ts");
        requestEx.ts=Long.parseLong(ts);
        logForMongoService.mongo1save(requestEx);
        String s5= request.getRemoteAddr();

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("user_token")) {
                return cookie.getValue();
            }
        }
        return "";
    }

    //pjp参数由spring注入
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        //获取当前正在执行的方法
        Method method = ms.getMethod();
        //判断该方法是否被注解@Token标记
        if (method.isAnnotationPresent(AuthToken.class)) {
            //执行token验证
        }

        //继续执行方法
        return pjp.proceed();
    }
}
