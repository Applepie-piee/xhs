package com.applepie.xhsAuth;

import com.applepie.jackson.utils.JsonUtil;
import com.applepie.xhsAuth.domain.dataobject.UserDO;
import com.applepie.xhsAuth.domain.mapper.UserDOMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
class XhsAuthApplicationTests {
    @Resource
    private UserDOMapper userDOMapper;

    /**
     * 测试插入数据
     */
    @Test
    void testInsert() {
        UserDO userDO = UserDO.builder()
                .username("苹果派派")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        userDOMapper.insert(userDO);
    }
    /**
     * 查询数据
     */
    @Test
    void testSelect() {
        // 查询主键 ID 为 2 的记录
        UserDO userDO = userDOMapper.selectByPrimaryKey(2L);
        log.info("User: {}", JsonUtil.toJsonString(userDO));
    }
    /**
     * 更新数据
     */
    @Test
    void testUpdate() {
        UserDO userDO = UserDO.builder()
                .id(1L)
                .username("苹果派")
                .updateTime(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .build();

        // 根据主键 ID 更新记录
        userDOMapper.updateByPrimaryKey(userDO);
    }
    @Test
    void testDelete() {
        // 删除主键 ID 为 4 的记录
        userDOMapper.deleteByPrimaryKey(4L);
    }
}
