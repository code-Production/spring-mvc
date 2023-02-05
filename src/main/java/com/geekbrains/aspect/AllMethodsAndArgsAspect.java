package com.geekbrains.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class AllMethodsAndArgsAspect {

    //SecurityFilter вызывает NPE -  Cannot invoke "org.apache.commons.logging.Log.isDebugEnabled()" because "this.logger" is null
    @Pointcut("within(com.geekbrains..*) && !within(com.geekbrains.security.SecurityFilter)")
    public void allMethodsPointcut() {
    }

    @Before("allMethodsPointcut()")
    public void beforeAllMethodsWithArgsPointcut(JoinPoint jp){
        String className = jp.getTarget().getClass().getName();
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        String methodName = methodSignature.getMethod().getName();
        String methodArgs = Arrays.toString(methodSignature.getMethod().getParameterTypes());
        log.debug("Method call occurred: {}#{}({})", className, methodName, methodArgs);
    }

}
