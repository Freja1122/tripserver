package com.tongji.testserver.service;

import com.tongji.testserver.domain.Destination;
import com.tongji.testserver.domain.Path;
import com.tongji.testserver.domain.Tag;

import java.util.Date;

public interface PathService {

    /**
     * 新增一个一条待规划的路径
     */
    Path createPath();

    /**
     * 为路径新增目标节点
     */
    void addDestination(long pathId, Destination destination);

    /**
     * 获得推荐路径
     */
    String getRecommendPath(long id);

    /**
     * 获得推荐时间
     */
    String getRecommendTime(long id);

    /**
     * 设置偏好出行时间
     */
    void setPreferLeaveTime(long id, Date date);

    /**
     * 删除一条轨迹
     */
    void deletePathByPathId(long id);

    /**
     * 删除当前用户的所有轨迹
     */
    void deleteAllPath();

    /**
     * 设置描述
     */
    void setDescription(long id, String description);

    /**
     * 设置路径标题
     */
    void setTitle(long id, String title);

    /**
     * 设置路径类型
     */
    void setType(long id, String type);

    /**
     * 设置标签
     */
    void setTag(long id, String tagTitle);

    /**
     * 设置提醒时间
     */
    void setNoticeTime(long id, Date time);

    /**
     * 设置提醒时间带着描述
     */
    void setNoticeTimeWithDes(long id, String description);

    /**
     * 收藏路径
     */
    void setPathFavorites(long id);

    /**
     * 设置标签
     */
    void setTag(long id, Tag tag);


}