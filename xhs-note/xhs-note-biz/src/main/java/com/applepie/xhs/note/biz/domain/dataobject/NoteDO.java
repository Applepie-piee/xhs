package com.applepie.xhs.note.biz.domain.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDO {
    private Long id;

    private String title;

    private Boolean isContentEmpty;

    private Long creatorId;

    private Long topicId;

    private String topicName;

    private Boolean isTop;

    private Byte type;

    private String imgUris;

    private String videoUri;

    private Byte visible;

    private Date createTime;

    private Date updateTime;

    private Byte status;
}