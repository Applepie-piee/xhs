package com.applepie.xhsAuth.controller;

import com.applepie.common.response.Response;
import com.applepie.framework.biz.operationlog.aspect.ApiOperationLog;
import com.applepie.xhsAuth.model.vo.user.UserLoginReqVO;
import com.applepie.xhsAuth.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 苹果派派
 * @date: 2026/6/30 00:00
 * @version: v1.0.0
 * @description: TODO
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ApiOperationLog(description = "用户登录/注册")
    public Response<String> loginAndRegister(@Validated @RequestBody UserLoginReqVO userLoginReqVO) {
        return userService.loginAndRegister(userLoginReqVO);
    }

}
