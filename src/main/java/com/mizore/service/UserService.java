package com.mizore.service;

import com.mizore.entity.User;

import java.util.List;

public interface UserService {


    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    boolean login(User user);


    /**
     * 用户注册
     * @param user
     */
    boolean register(User user);


    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllUser();
}
