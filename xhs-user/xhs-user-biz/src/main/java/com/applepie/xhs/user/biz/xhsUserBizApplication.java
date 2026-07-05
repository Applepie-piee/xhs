package com.applepie.xhs.user.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.applepie.xhs.user.biz.domain.mapper")
@EnableFeignClients("com.applepie.xhs")
public class xhsUserBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(xhsUserBizApplication.class, args);
    }

}
