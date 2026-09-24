package com.applepie.xhs.note.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: 苹果派派
 * @date: 2026/9/23 19:53
 * @version: v1.0.0
 * @description: 笔记可见性
 **/

@Getter
@AllArgsConstructor
public enum NoteVisibleEnum {

    PUBLIC(0), // 公开，所有人可见
    PRIVATE(1); // 仅自己可见

    private final Integer code;

}

