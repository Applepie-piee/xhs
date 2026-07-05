package com.applepie.xhs.oss.biz.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: 苹果派派
 * @date: 2026/7/5 14:24
 * @version: v1.0.0
 * @description: 文件策略接口
 **/
public interface FileStrategy {

    /**
     * 文件上传
     * 
     * @param file
     * @param bucketName
     * @return
     */
    String uploadFile(MultipartFile file, String bucketName);

}

