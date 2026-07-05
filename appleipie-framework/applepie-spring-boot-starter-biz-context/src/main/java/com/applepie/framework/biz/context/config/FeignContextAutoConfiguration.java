package com.applepie.framework.biz.context.config;

import com.applepie.framework.biz.context.interceptor.FeignRequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author: 苹果派派
 * @date: 2026/7/6 02:10
 * @version: v1.0.0
 * @description: Feign 请求拦截器自动配置
 **/
@AutoConfiguration
public class FeignContextAutoConfiguration {

    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}

