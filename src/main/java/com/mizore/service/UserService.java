package com.mizore.service;

import com.mizore.entity.User;

public interface UserService {


    /**
     * 用户登录
     * @param user
     */
    void login(User user);
}
