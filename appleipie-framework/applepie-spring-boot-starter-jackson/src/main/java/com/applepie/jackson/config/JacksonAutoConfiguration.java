package com.applepie.jackson.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class JacksonAutoConfiguration {
    @Bean
    public JacksonConfig jacksonConfig() {
        return new JacksonConfig();
    }
}
