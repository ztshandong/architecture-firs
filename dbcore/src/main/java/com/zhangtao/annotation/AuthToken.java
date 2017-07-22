package com.zhangtao.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AuthToken {

}
