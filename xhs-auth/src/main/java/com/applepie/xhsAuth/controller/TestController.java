package com.applepie.xhsAuth.controller;

import com.applepie.common.response.Response;
import com.applepie.framework.biz.operationlog.aspect.ApiOperationLog;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestController {
    @GetMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public Response<String> test() {
        return Response.success("早安喵");
    }
    @GetMapping("/test2")
    @ApiOperationLog(description = "测试接口2")
    public Response<User> test2() {
        return Response.success(User.builder()
                .nickName("关注苹果派派喵")
                .createTime(LocalDateTime.now())
                .build());
    }
    @GetMapping("/test3")
    @ApiOperationLog(description = "测试接口3")
    public Response<User> test3(@RequestBody @Validated User user) {
        return Response.success(user);
    }
}
