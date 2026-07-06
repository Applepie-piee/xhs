package com.applepie.xhs.kv.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 苹果派派
 * @date: 2026/7/7 01:43
 * @version: v1.0.0
 * @description: 笔记内容删除
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteNoteContentReqDTO {

    @NotBlank(message = "笔记 ID 不能为空")
    private String noteId;

}

