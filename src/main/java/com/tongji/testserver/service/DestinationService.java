package com.tongji.testserver.service;

import com.tongji.testserver.domain.Tag;

import java.sql.Time;

public interface DestinationService {

    /**
     * 新增目标节点
     */
    void create(String latitude,String longitude, int dayOfWeek, Time arriveTime, int Order);

    /**
     * 获取某点某天热度
     */
    String getHotExtent(String latitude,String longitude, int dayOfWeek, Time arriveTime);

    /**
     * 设置描述
     */
    void setDescription(long id, String description);

    /**
     * 设置标题
     */
    void setTitle(long id, String title);

    /**
     * 设置类型
     */
    void setType(long id, String type);

    /**
     * 设置标签
     */
    void setTag(long id, Tag tag);
}
