package com.applepie.xhs.oss.biz.controller;

import com.applepie.common.response.Response;
import com.applepie.framework.biz.operationlog.aspect.ApiOperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 苹果派派
 * @date: 2026/7/6 01:24
 * @version: v1.0.0
 * @description: Feign 测试接口
 **/
@RestController
@RequestMapping("/file")
@Slf4j
public class TestFeignController {

    @PostMapping(value = "/test")
    @ApiOperationLog(description = "Feign 测试接口")
    public Response<?> test() {
        return Response.success();
    }

}
