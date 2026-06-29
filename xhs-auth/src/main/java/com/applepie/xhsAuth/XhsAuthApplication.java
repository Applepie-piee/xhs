package com.applepie.xhsAuth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.applepie.xhsAuth.domain.mapper")
public class XhsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(XhsAuthApplication.class, args);
    }

}
