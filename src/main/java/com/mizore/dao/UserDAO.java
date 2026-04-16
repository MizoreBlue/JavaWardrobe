package com.mizore.dao;

import com.mizore.entity.User;

public interface UserDAO {


    /**
     * 用户登录
     * @param username
     * @return
     */
    User getUser(String username);
}
