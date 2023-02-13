package com.geekbrains.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("@within(com.geekbrains.aspect.Timer)")
    public void classAnnotatedPointcut() {
    }

    @Pointcut("@annotation(Timer)")
    public void methodAnnotatedPointcut() {

    }

    @Pointcut("classAnnotatedPointcut() || methodAnnotatedPointcut()")
    public void targetPointcut() {
    }

    @Around("targetPointcut()")
    public Object aroundAnnotatedMethods(ProceedingJoinPoint pjp) throws Throwable {
        Object result;
        long timer = System.currentTimeMillis();
        try {
            result = pjp.proceed();
        } finally {
            timer = System.currentTimeMillis() - timer;
            String className = pjp.getTarget().getClass().getName();
            String methodName = pjp.getSignature().getName();
            log.info("{}#{}: {} ms", className, methodName, timer);
        }
        return result;
    }
}
