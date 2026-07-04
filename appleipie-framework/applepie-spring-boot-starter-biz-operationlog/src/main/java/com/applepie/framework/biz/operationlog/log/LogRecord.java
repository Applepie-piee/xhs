package com.applepie.framework.biz.operationlog.log;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRecord {
    String value() default ""; // 用于描述操作内容，比如 "用户退出登录"
}