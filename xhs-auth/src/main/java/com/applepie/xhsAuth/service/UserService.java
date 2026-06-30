package com.applepie.xhsAuth.service;


import com.applepie.common.response.Response;
import com.applepie.xhsAuth.model.vo.user.UserLoginReqVO;

/**
 * @author: 苹果派派
 * @date: 2026/6/30 00:00
 * @version: v1.0.0
 * @description: TODO
 **/
public interface UserService {

    /**
     * 登录与注册
     * @param userLoginReqVO
     * @return
     */
    Response<String> loginAndRegister(UserLoginReqVO userLoginReqVO);
}

