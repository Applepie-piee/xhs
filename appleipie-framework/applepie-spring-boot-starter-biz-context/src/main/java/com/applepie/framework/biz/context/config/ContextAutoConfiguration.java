package com.applepie.framework.biz.context.config;

import com.applepie.framework.biz.context.filter.HeaderUserId2ContextFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)

public class ContextAutoConfiguration {
    @Bean
    public FilterRegistrationBean<HeaderUserId2ContextFilter> headerUserId2ContextFilterRegistration() {
        FilterRegistrationBean<HeaderUserId2ContextFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new HeaderUserId2ContextFilter());
        registration.addUrlPatterns("/*");
        registration.setName("headerUserId2ContextFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
}
