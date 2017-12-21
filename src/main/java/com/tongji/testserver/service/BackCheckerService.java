package com.tongji.testserver.service;

/**
 * @program: testserver
 * @description:
 * @author: Annntn
 * @create: 2017-12-21 09:21
 **/

public interface BackCheckerService {

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
    Integer getAllCheckers();

    /**
     * 删除所有用户
     */
    void deleteAllCheckers();

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


}
