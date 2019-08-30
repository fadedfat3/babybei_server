package com.zhumingbei.babybei_server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
/**
 @author: fadedfate
 */
public class ExceptionHandlerLogAspect {
    @Pointcut("execution(public * com.zhumingbei.babybei_server.exception.handler..*.*(..))")
    public void exceptionLog() {

    }

    @Around("exceptionLog()")
    public Object handler(ProceedingJoinPoint point) throws Throwable {
        log.info("Exception Handler: " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        log.info("Exception Handler Args: {}", point.getArgs());
        //执行连接点
        Object result = point.proceed();
        log.info("Exception Handler result: " + result);
        //返回连接点的结果
        return result;
    }

}
