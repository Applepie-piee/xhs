package com.applepie.xhs.oss.biz.service.impl;


import com.applepie.common.response.Response;
import com.applepie.xhs.oss.biz.service.FileService;
import com.applepie.xhs.oss.biz.strategy.FileStrategy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: 苹果派派
 * @date: 2026/7/5 14:24
 * @version: v1.0.0
 * @description: TODO
 **/
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Resource
    private FileStrategy fileStrategy;

    private static final String BUCKET_NAME = "xhs-applepie";

    @Override
    public Response<?> uploadFile(MultipartFile file) {
        // 上传文件
        String url = fileStrategy.uploadFile(file, BUCKET_NAME);

        return Response.success(url);
    }
}

