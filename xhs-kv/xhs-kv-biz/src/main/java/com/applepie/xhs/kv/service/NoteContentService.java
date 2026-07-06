package com.applepie.xhs.kv.service;

import com.applepie.common.response.Response;
import com.applepie.xhs.kv.dto.req.AddNoteContentReqDTO;
import com.applepie.xhs.kv.dto.req.DeleteNoteContentReqDTO;
import com.applepie.xhs.kv.dto.req.FindNoteContentReqDTO;
import com.applepie.xhs.kv.dto.rsp.FindNoteContentRspDTO;

/**
 * @author: 苹果派派
 * @date: 2026/7/7 01:13
 * @version: v1.0.0
 * @description: 笔记内容存储业务
 **/
public interface NoteContentService {

    /**
     * 添加笔记内容
     * 
     * @param addNoteContentReqDTO
     * @return
     */
    Response<?> addNoteContent(AddNoteContentReqDTO addNoteContentReqDTO);
    /**
     * 查询笔记内容
     *
     * @param findNoteContentReqDTO
     * @return
     */
    Response<FindNoteContentRspDTO> findNoteContent(FindNoteContentReqDTO findNoteContentReqDTO);
    /**
     * 删除笔记内容
     *
     * @param deleteNoteContentReqDTO
     * @return
     */
    Response<?> deleteNoteContent(DeleteNoteContentReqDTO deleteNoteContentReqDTO);

}
