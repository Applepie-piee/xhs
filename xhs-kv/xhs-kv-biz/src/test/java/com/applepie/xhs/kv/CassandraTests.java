package com.applepie.xhs.kv;

import com.applepie.jackson.utils.JsonUtil;
import com.applepie.xhs.kv.domain.dataobject.NoteContentDO;
import com.applepie.xhs.kv.domain.repository.NoteContentRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Slf4j
class CassandraTests {

    @Resource
    private NoteContentRepository noteContentRepository;

    /**
     * 测试插入数据
     */
    @Test
    void testInsert() {
        for (int i = 0; i < 5; i++) {
            NoteContentDO nodeContent = NoteContentDO.builder()
                    .id(UUID.randomUUID())
                    .content("Java测试笔记内容插入" + i)
                    .build();
//            System.out.println("nodeContent = " + nodeContent);
            noteContentRepository.save(nodeContent);
        }
    }
    /**
     * 测试查询数据
     */
    @Test
    void testSelect() {
        Optional<NoteContentDO> optional = noteContentRepository.findById(UUID.fromString("2c6fa7df-3541-4f6f-b103-e883f2ecc0fd"));
        optional.ifPresent(noteContentDO -> log.info("查询结果：{}", JsonUtil.toJsonString(noteContentDO)));
    }
    /**
     * 测试删除数据
     */
    @Test
    void testDelete() {
        noteContentRepository.deleteById(UUID.fromString("2c6fa7df-3541-4f6f-b103-e883f2ecc0fd"));
    }
}
