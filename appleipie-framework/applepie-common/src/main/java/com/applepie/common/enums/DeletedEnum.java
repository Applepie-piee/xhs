package com.applepie.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: 苹果派派
 * @date: 2026/6/30 00:00
 * @description: 逻辑删除
 **/
@Getter
@AllArgsConstructor
public enum DeletedEnum {

    YES(true),
    NO(false);

    private final Boolean value;
}
