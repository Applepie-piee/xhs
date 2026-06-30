package com.applepie.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: 苹果派派
 * @date: 2026/6/30 00:00
 * @description: 状态
 **/
@Getter
@AllArgsConstructor
public enum StatusEnum {
    // 启用
    ENABLE(0),
    // 禁用
    DISABLED(1);

    private final Integer value;
}

