package com.applepie.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;
import com.applepie.gateway.constant.RedisKeyConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 苹果派派
 * @date: 2026/7/3 00:00
 * @version: v1.0.0
 * @description: 自定义权限验证接口扩展
 **/
/**
 * 自定义权限验证接口扩展
 */
@Component
@Slf4j
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        log.info("## 获取用户权限列表, loginId: {}", loginId);

        // 构建 用户-角色 Redis Key
        String userRolesKey = RedisKeyConstants.buildUserRoleKey(Long.valueOf(loginId.toString()));
        String useRolesValue = redisTemplate.opsForValue().get(userRolesKey);

        if (StringUtils.isBlank(useRolesValue)) {
            return Collections.emptyList(); // 修改：返回空集合，不要返回 null
        }

        List<String> userRoleKeys;
        try {
            userRoleKeys = objectMapper.readValue(useRolesValue, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error("==> 解析用户角色失败, loginId: {}", loginId, e);
            return Collections.emptyList(); // 修改：返回空集合
        }

        if (CollUtil.isNotEmpty(userRoleKeys)) {
            // 构建 角色-权限 Redis Key 集合
            List<String> rolePermissionsKeys = userRoleKeys.stream()
                    .map(RedisKeyConstants::buildRolePermissionsKey)
                    .toList();

            // 批量获取
            List<String> rolePermissionsValues = redisTemplate.opsForValue().multiGet(rolePermissionsKeys);

            if (CollUtil.isNotEmpty(rolePermissionsValues)) {
                List<String> permissions = Lists.newArrayList();

                // 遍历所有角色的权限集合
                rolePermissionsValues.forEach(jsonValue -> {
                    // ⚠️ 【关键修改】如果取出来是 null，直接跳过，不要解析！
                    if (StringUtils.isBlank(jsonValue)) {
                        return;
                    }

                    try {
                        List<String> rolePermissions = objectMapper.readValue(jsonValue, new TypeReference<>() {});
                        if (CollUtil.isNotEmpty(rolePermissions)) {
                            permissions.addAll(rolePermissions);
                        }
                    } catch (JsonProcessingException e) {
                        log.error("==> JSON 解析错误, 无效的JSON值: {}", jsonValue, e);
                    }
                });

                return permissions;
            }
        }
        return Collections.emptyList(); // 最后兜底返回空集合
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        log.info("## 获取用户角色列表, loginId: {}", loginId);

        // 构建 用户-角色 Redis Key
        String userRolesKey = RedisKeyConstants.buildUserRoleKey(Long.valueOf(loginId.toString()));
        log.info("## Redis Key: {}", userRolesKey);
        // 根据用户 ID ，从 Redis 中获取该用户的角色集合
        String useRolesValue = redisTemplate.opsForValue().get(userRolesKey);
        log.info("## Redis Value: {}", useRolesValue);
        if (StringUtils.isBlank(useRolesValue)) {
            return null;
        }

        // 将 JSON 字符串转换为 List<String> 集合
        try {
            return objectMapper.readValue(useRolesValue, new TypeReference<>() {});
        } catch (Exception e) {
            log.error("解析用户角色失败, loginId: {}", loginId, e);
            return Collections.emptyList();
        }
    }

}
