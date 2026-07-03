package com.applepie.xhsAuth.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.applepie.xhsAuth.alarm.AlarmInterface;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@Slf4j
@RefreshScope
public class TestController {

//    @Value("${rate-limit.api.limit}")
    private Integer limit;
    @Autowired
    private ConfigurableEnvironment environment;
    @Resource
    private AlarmInterface alarm;


    @GetMapping("/test")
    public String test() {
        log.info("当前限流阈值为: {}", limit);

        return "当前限流阈值为: " + limit;
    }
    @GetMapping("/debug")
    public String debug() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== 所有包含 'rate-limit' 的属性源 ===\n");

        environment.getPropertySources().forEach(propertySource -> {
            Object value = propertySource.getProperty("rate-limit.api.limit");
            if (value != null) {
                sb.append("属性源名称: ").append(propertySource.getName())
                        .append("\n值: ").append(value)
                        .append("\n属性源类型: ").append(propertySource.getClass().getName())
                        .append("\n---\n");
            }
        });

        // 打印完整的属性源顺序
        sb.append("\n=== 完整属性源列表(从上到下优先级从高到低) ===\n");
        environment.getPropertySources().forEach(ps -> {
            sb.append(ps.getName()).append("\n");
        });

        sb.append("\n最终值: ").append(environment.getProperty("rate-limit.api.limit"));

        return sb.toString().replace("\n", "<br>"); // 浏览器友好换行
    }
    @GetMapping("/test2")
    public String test2() throws Exception {
        Properties prop = new Properties();
        prop.put("serverAddr", "localhost:8848");
        ConfigService cs = NacosFactory.createConfigService(prop);
        String raw = cs.getConfig("xhs-auth-lanmei", "DEFAULT_GROUP", 3000);
        return "Nacos原始内容: " + raw;
    }
    @GetMapping("/alarm")
    public String sendAlarm() {
        alarm.send("man,what can i say?Manba out!");
        return "alarm success";
    }
}
