package com.applepie.xhs.user.biz.service;


import com.applepie.common.response.Response;
import com.applepie.xhs.user.biz.model.vo.UpdateUserInfoReqVO;
import com.applepie.xhs.user.dto.req.FindUserByPhoneReqDTO;
import com.applepie.xhs.user.dto.req.RegisterUserReqDTO;
import com.applepie.xhs.user.dto.req.UpdateUserPasswordReqDTO;
import com.applepie.xhs.user.dto.resp.FindUserByPhoneRspDTO;

/**
 * @author: 苹果派派
 * @date: 2026/7/6 00:53
 * @version: v1.0.0
 * @description: 用户业务
 **/
public interface UserService {

    /**
     * 更新用户信息
     *
     * @param updateUserInfoReqVO
     * @return
     */
    Response<?> updateUserInfo(UpdateUserInfoReqVO updateUserInfoReqVO);
    /**
     * 用户注册
     *
     * @param registerUserReqDTO
     * @return
     */
    Response<Long> register(RegisterUserReqDTO registerUserReqDTO);
    /**
     * 根据手机号查询用户信息
     *
     * @param findUserByPhoneReqDTO
     * @return
     */
    Response<FindUserByPhoneRspDTO> findByPhone(FindUserByPhoneReqDTO findUserByPhoneReqDTO);
    /**
     * 更新密码
     *
     * @param updateUserPasswordReqDTO
     * @return
     */
    Response<?> updatePassword(UpdateUserPasswordReqDTO updateUserPasswordReqDTO);

}

