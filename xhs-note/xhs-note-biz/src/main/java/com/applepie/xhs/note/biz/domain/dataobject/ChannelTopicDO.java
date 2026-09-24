package com.applepie.xhs.note.biz.domain.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelTopicDO {
    private Long id;

    private Long channelId;

    private Long topicId;

    private Date createTime;

    private Date updateTime;

}