package com.applepie.xhs.kv.enums;

import com.applepie.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: 苹果派派
 * @date: 2026/7/7 01:23
 * @description: 响应异常码
 **/
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {

    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("KV-10000", "出错了喵，正在努力修复中ing"),
    PARAM_NOT_VALID("KV-10001", "参数错误了哦喵"),

    // ----------- 业务异常状态码 -----------
    NOTE_CONTENT_NOT_FOUND("KV-20000", "该笔记内容不存在捏"),
    ;

    // 异常码
    private final String errorCode;
    // 错误信息
    private final String errorMessage;

}
