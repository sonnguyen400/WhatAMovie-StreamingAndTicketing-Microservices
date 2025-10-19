package com.sonnguyen.common.web.configuration.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FilterExceptionHandler {

    @AfterThrowing(value = "execution(* com.sonnguyen.common.web.configuration.impl.JwtTokenProviderImpl*.verify(..))", throwing = "exception")
    public void catchException(JoinPoint joinPoint, Exception exception) {
    }
}
