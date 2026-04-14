package com.mizore.service.impl;

import com.mizore.dao.UserDao;
import com.mizore.dao.impl.UserDaoImpl;
import com.mizore.entity.User;
import com.mizore.service.UserService;

public class UserServiceImpl implements UserService {

//   持久层
    private UserDao userDao = new UserDaoImpl();


    /**
     * 用户登录
     * @param user
     */
    public void login(User user) {
//        TODO 实习用户登录
    }
}
