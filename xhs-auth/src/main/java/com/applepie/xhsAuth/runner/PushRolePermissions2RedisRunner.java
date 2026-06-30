package com.applepie.xhsAuth.runner;

import cn.hutool.core.collection.CollUtil;
import com.applepie.jackson.utils.JsonUtil;
import com.applepie.xhsAuth.constant.RedisKeyConstants;
import com.applepie.xhsAuth.domain.dataobject.PermissionDO;
import com.applepie.xhsAuth.domain.dataobject.RoleDO;
import com.applepie.xhsAuth.domain.dataobject.RolePermissionRelDO;
import com.applepie.xhsAuth.domain.mapper.PermissionDOMapper;
import com.applepie.xhsAuth.domain.mapper.RoleDOMapper;
import com.applepie.xhsAuth.domain.mapper.RolePermissionRelDOMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: 苹果派派
 * @date: 2026/6/30 00:00
 * @version: v1.0.0
 * @description: 推送角色权限数据到 Redis 中
 **/
@SuppressWarnings("all")
@Component
@Slf4j
public class PushRolePermissions2RedisRunner implements ApplicationRunner {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private RoleDOMapper roleDOMapper;
    @Resource
    private PermissionDOMapper permissionDOMapper;
    @Resource
    private RolePermissionRelDOMapper rolePermissionRelDOMapper;

    // 权限同步标记 Key
    private static final String PUSH_PERMISSION_FLAG = "push.permission.flag";

    /**
     * @PostConstruct：适合简单的初始化逻辑，执行时机较早。
     * ApplicationRunner 和 CommandLineRunner：适合需要访问命令行参数的初始化逻辑，执行时机在 Spring Boot 应用启动完成后。
     * ApplicationReadyEvent 监听器：适合在整个应用准备好后执行的初始化逻辑。
     * SmartInitializingSingleton：适合需要在所有单例 bean 初始化完成后执行的初始化逻辑。
     * InitializingBean：适合需要在 bean 属性设置完成后执行的初始化逻辑。
     */
    @Override
    public void run(ApplicationArguments args) {
        log.info("==> 服务启动，开始同步角色权限数据到 Redis 中...");

        try {
            // 是否能够同步数据: 原子操作，只有在键 PUSH_PERMISSION_FLAG 不存在时，才会设置该键的值为 "1"，并设置过期时间为 1 天
            boolean canPushed = redisTemplate.opsForValue().setIfAbsent(PUSH_PERMISSION_FLAG, "1", 1, TimeUnit.DAYS);

            // 如果无法同步权限数据
            if (!canPushed) {
                log.warn("==> 角色权限数据已经同步至 Redis 中，不再同步...");
                return;
            }
            // 查询出所有角色
            List<RoleDO> roleDOS = roleDOMapper.selectEnabledList();

            if (CollUtil.isNotEmpty(roleDOS)) {
                // 拿到所有角色的 ID
                List<Long> roleIds = roleDOS.stream().map(RoleDO::getId).toList();

                // 根据角色 ID, 批量查询出所有角色对应的权限
                List<RolePermissionRelDO> rolePermissionDOS = rolePermissionRelDOMapper.selectByRoleIds(roleIds);
                // 按角色 ID 分组, 每个角色 ID 对应多个权限 ID
                Map<Long, List<Long>> roleIdPermissionIdsMap = rolePermissionDOS.stream().collect(
                        Collectors.groupingBy(RolePermissionRelDO::getRoleId,
                                Collectors.mapping(RolePermissionRelDO::getPermissionId, Collectors.toList()))
                );

                // 查询 APP 端所有被启用的权限
                List<PermissionDO> permissionDOS = permissionDOMapper.selectAppEnabledList();
                // 权限 ID - 权限 DO
                Map<Long, PermissionDO> permissionIdDOMap = permissionDOS.stream().collect(
                        Collectors.toMap(PermissionDO::getId, permissionDO -> permissionDO)
                );

                // 组织 角色ID-权限 关系
                Map<Long, List<PermissionDO>> roleIdPermissionDOMap = Maps.newHashMap();

                // 循环所有角色
                roleDOS.forEach(roleDO -> {
                    // 当前角色 ID
                    Long roleId = roleDO.getId();
                    // 当前角色 ID 对应的权限 ID 集合
                    List<Long> permissionIds = roleIdPermissionIdsMap.get(roleId);
                    if (CollUtil.isNotEmpty(permissionIds)) {
                        List<PermissionDO> perDOS = Lists.newArrayList();
                        permissionIds.forEach(permissionId -> {
                            // 根据权限 ID 获取具体的权限 DO 对象
                            PermissionDO permissionDO = permissionIdDOMap.get(permissionId);
                            if (Objects.nonNull(permissionDO)) {
                                perDOS.add(permissionDO);
                            }
                        });
                        roleIdPermissionDOMap.put(roleId, perDOS);
                    }
                });

                // 同步至 Redis 中，方便后续网关查询鉴权使用
                roleIdPermissionDOMap.forEach((roleId, permissions) -> {
                    String key = RedisKeyConstants.buildRolePermissionsKey(roleId);
                    redisTemplate.opsForValue().set(key, JsonUtil.toJsonString(permissions));
                });
            }

            log.info("==> 服务启动，成功同步角色权限数据到 Redis 中...");
        } catch (Exception e) {
            log.error("==> 同步角色权限数据到 Redis 中失败: ", e);
        }
    }
}

