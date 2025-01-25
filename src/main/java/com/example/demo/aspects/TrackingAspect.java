package com.example.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class TrackingAspect {
    @Around("@annotation(com.example.demo.aspects.TrackUserAction)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Вызван метод " + joinPoint.getSignature().getName() +
                " с параметрами " + Arrays.toString(joinPoint.getArgs()));
        Object proceed = joinPoint.proceed();
        System.out.println("Метод завершил работу");
        return proceed;
    }
}
