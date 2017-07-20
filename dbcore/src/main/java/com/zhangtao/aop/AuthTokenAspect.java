//package com.zhangtao.aop;
//
//import com.zhangtao.annotation.AuthToken;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//
//
///**
// * Created by zhangtao on 2017/7/20.
// */
//@Aspect
//@Component
//public class AuthTokenAspect {
//
//    // 定义一个 Pointcut, 使用 切点表达式函数 来描述对哪些 Join point 使用 advise.
//    @Pointcut("@annotation(com.zhangtao.annotation.AuthToken)")
//    public void pointcut() {
//    }
//
//    // 定义 advise
//    @Around("pointcut()")
//    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                .getRequest();
//
//        // 检查用户所传递的 token 是否合法
//        String token = getUserToken(request);
//        if (!token.equalsIgnoreCase("123456")) {
//            return "错误, 权限不合法!";
//        }
//
//        return joinPoint.proceed();
//    }
//
//    private String getUserToken(HttpServletRequest request) {
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
//    }
//
//    //pjp参数由spring注入
//    public Object execute(ProceedingJoinPoint pjp) throws  Throwable{
//        MethodSignature ms = (MethodSignature) pjp.getSignature();
//        //获取当前正在执行的方法
//        Method method = ms.getMethod();
//        //判断该方法是否被注解@Token标记
//        if(method.isAnnotationPresent(AuthToken.class)){
//            //执行token验证
//        }
//
//        //继续执行方法
//        return pjp.proceed();
//    }
//}
