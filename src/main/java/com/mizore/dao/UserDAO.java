package com.mizore.dao;

import com.mizore.entity.User;

import java.util.List;

public interface UserDAO {


    /**
     * 用户登录
     * @param username
     * @return
     */
    User getUser(String username);


    /**
     * 用户注册
     * @param user
     */
    void insert(User user);


    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllUser();
}
