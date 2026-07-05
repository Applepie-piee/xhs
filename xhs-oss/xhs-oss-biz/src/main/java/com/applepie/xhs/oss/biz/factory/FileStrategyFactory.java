package com.applepie.xhs.oss.biz.factory;

import com.applepie.xhs.oss.biz.strategy.FileStrategy;
import com.applepie.xhs.oss.biz.strategy.impl.AliyunOSSFileStrategy;
import com.applepie.xhs.oss.biz.strategy.impl.MinioFileStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 苹果派派
 * @date: 2026/7/5 14:24
 * @version: v1.0.0
 * @description: TODO
 **/
@Configuration
@RefreshScope
public class FileStrategyFactory {

    @Value("${storage.type}")
    private String strategyType;

    @Bean
    public FileStrategy getFileStrategy() {
        if (StringUtils.equals(strategyType, "minio")) {
            return new MinioFileStrategy();
        } else if (StringUtils.equals(strategyType, "aliyun")) {
            return new AliyunOSSFileStrategy();
        }

        throw new IllegalArgumentException("不可用的存储类型");
    }

}

