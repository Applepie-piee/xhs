package com.applepie.framework.biz.operationlog.aspect;

import com.applepie.framework.biz.operationlog.log.LogRecord;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LogAspect {

    // 前置通知：在带有 @LogRecord 注解的方法执行前，打印日志
    @Before("@annotation(com.applepie.framework.biz.operationlog.log.LogRecord)")
    public void printLogBeforeMethod(JoinPoint joinPoint) {
        // 1. 获取当前执行的方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        
        // 2. 获取方法上的注解
        LogRecord logRecord = method.getAnnotation(LogRecord.class);
        
        // 3. 获取方法名 和 注解中的描述
        String methodName = method.getName();
        String operationDesc = logRecord.value();
        
        // 4. 打印日志：方法名 + 操作描述（你如果在这里能取到 userId，也可以加上）
        // 注意：@Before 切面在执行 Controller 方法前执行，此时 LoginUserContextHolder 通常已经存了 UserId。
        log.info("方法: {}, {}" , methodName, operationDesc);
    }
}