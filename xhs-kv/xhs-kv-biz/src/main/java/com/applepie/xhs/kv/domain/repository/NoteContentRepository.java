package com.applepie.xhs.kv.domain.repository;

import com.applepie.xhs.kv.domain.dataobject.NoteContentDO;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

/**
 * @author: 苹果派派
 * @date: 2026/7/7 01:00
 * @version: v1.0.0
 * @description: TODO
 **/
public interface NoteContentRepository extends CassandraRepository<NoteContentDO, UUID> {

}

