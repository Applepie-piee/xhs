package com.applepie.xhs.oss.biz.service;

import com.applepie.common.response.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: 苹果派派
 * @date: 2026/7/5 14:24
 * @version: v1.0.0
 * @description: TODO
 **/
public interface FileService {

    /**
     * 上传文件
     * 
     * @param file
     * @return
     */
    Response<?> uploadFile(MultipartFile file);
}
