package com.spring.rest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

                        //returnType className.methodName(args)
    //* is a wildcard for all classnames, method name, and return type. (..) is a wildcard for all arguments
    //We can || and mention other class or method with this logger.
    //We have before, after, Before/after returning-after successful execution, before/after throwing if exception is thrown
    @Before("execution(* com.spring.rest.service.JobService.getAllJobs(..))")
    public void callMethod(JoinPoint jp){
        LOGGER.info("Method called -> "+ jp.getSignature().getName());
    }

    //Around runs before and after execution of specified method(s) -> ProceedingJoinPoint gives you access to continue execute the method.
    @Around("execution(* com.spring.rest.service.JobService.*(..))")
    public Object performanceMetric(ProceedingJoinPoint pjp){
        Object obj = null;
        long start = System.currentTimeMillis();
        try {
            obj = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        LOGGER.info("Time taken by "+ pjp.getSignature().getName() +" method" + "-> " + (end-start) + " ms");
        return obj;
    }

    //We can even change the request path variables with around. Mention the var in below advice and accept it in method declaration.
    //Also pass it as an array of objects to proceed method after correcting.
    @Around("execution(* com.spring.rest.service.JobService.getJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint pjp, int postId){
        Object obj = null;
        try {
            if(postId < 0){
                postId = -postId;
                LOGGER.warn("Id was negative, hence correcting it.");
            }
            Object[] objarr = {postId};
            obj = pjp.proceed(objarr);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
