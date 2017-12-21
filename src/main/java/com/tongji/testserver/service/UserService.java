package com.tongji.testserver.service;

import com.tongji.testserver.domain.Destination;
import com.tongji.testserver.domain.Path;
import com.tongji.testserver.domain.PsgHotFavorites;
import com.tongji.testserver.domain.Role;

import java.util.List;

public interface UserService {

    /**
     * 新增一个用户
     */
    void create(String name, Integer age);

    /**
     * 新增一个用户
     */
    void create(String userName, Integer passWord, String email);

    /**
     * 验证用户名密码是否正确
     */
    void login(String userName, Integer passWord, String email);

    /**
     * 根据name删除一个用户
     */
    void deleteByName(String name);

    /**
     * 获取用户总量
     */
    Integer getAllUsers();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();

    /**
     * 设置验证问题
     */
    void setCheckQuestion(String checkQuestion);

    /**
     * 设置验证答案
     */
    void setCheckAnswer(String checkAnswer);

    /**
     * 设置简介
     */
    void setIntroduction(String introduction);

    /**
     * 设置电话号码
     */
    void setUserPhone(String userPhone);

    /**
     * 为用户设置角色
     */
    void setRole(String roleTitle);

    /**
     * 为用户删除角色
     */
    void deleteRole(Role roleTitle);

    /**
     * 获取用户角色信息
     */
    List<Role> getRole(Role roleTitle);

    /**
     * 获取候车收藏夹
     */
    List<PsgHotFavorites> getPsgHotSpotFavorites();

    /**
     * 获取载客热点收藏夹
     */
    List<HotNodeService> getLoadHotSpotFavorites();

    /**
     * 获取目的地收藏夹
     */
    List<Destination> getDestinationFavorites();

    /**
     * 获取路径收藏夹
     */
    List<Path> getPathFavorites();

    /**
     * 获取路径历史记录
     */
    List<Path> getAllPath();




}