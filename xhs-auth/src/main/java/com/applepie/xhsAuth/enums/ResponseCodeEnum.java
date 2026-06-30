package com.applepie.xhsAuth.enums;

import com.applepie.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {

    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("AUTH-10000", "出错了喵，后台正在努力修复ing"),
    PARAM_NOT_VALID("AUTH-10001", "参数错误"),
    PARAM_IS_NULL("AUTH-10002", "验证码为空哦喵~"),
    VERIFICATION_CODE_SEND_FREQUENTLY("AUTH-20000", "请求太频繁，请3分钟后再试"),
    VERIFICATION_CODE_ERROR("AUTH-20001", "验证码错误了喵~"),
    ;

    // ----------- 业务异常状态码 -----------

    // 异常码
    private final String errorCode;
    // 错误信息
    private final String errorMessage;

}

