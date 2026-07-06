package com.applepie.xhs.kv.dto.api;

import com.applepie.common.response.Response;
import com.applepie.xhs.kv.dto.contant.ApiConstants;
import com.applepie.xhs.kv.dto.req.AddNoteContentReqDTO;
import com.applepie.xhs.kv.dto.req.DeleteNoteContentReqDTO;
import com.applepie.xhs.kv.dto.req.FindNoteContentReqDTO;
import com.applepie.xhs.kv.dto.rsp.FindNoteContentRspDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: 苹果派派
 * @date: 2026/7/7 01:20
 * @version: v1.0.0
 * @description: K-V 键值存储 Feign 接口
 **/
@FeignClient(name = ApiConstants.SERVICE_NAME)
public interface KeyValueFeignApi {

    String PREFIX = "/kv";

    @PostMapping(value = PREFIX + "/note/content/add")
    Response<?> addNoteContent(@RequestBody AddNoteContentReqDTO addNoteContentReqDTO);
    @PostMapping(value = PREFIX + "/note/content/find")
    Response<FindNoteContentRspDTO> findNoteContent(@RequestBody FindNoteContentReqDTO findNoteContentReqDTO);
    @PostMapping(value = PREFIX + "/note/content/delete")
    Response<?> deleteNoteContent(@RequestBody DeleteNoteContentReqDTO deleteNoteContentReqDTO);

}

