package com.fmi.aop.aspects;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Optional;

@Component
@Aspect
public class FibonacciAspect {

    @Pointcut("execution(* com.fmi.aop.services.FibonacciNumbersService.*(..))")
    public void printAFibNumber() {
    }

    @Before("@annotation(Countable)")
    public void beforeMethodStatistics(JoinPoint jp) throws Throwable {
        Long maxDepth = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(Countable.class).maxDepth();
        CodeSignature codeSignature = (CodeSignature) jp.getSignature();

        int x = 0;
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if (element.getMethodName().equals(((MethodSignature) jp.getSignature()).getMethod().getName())
                    && element.getClassName().equals(((MethodSignature) jp.getSignature()).getMethod().getDeclaringClass().getName())) {
                x++;
            }
            if (x > maxDepth) throw new Exception("Max call");
        }
    }

}