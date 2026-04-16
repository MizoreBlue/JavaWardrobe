package com.mizore.service.impl;

import com.mizore.dao.UserDAO;
import com.mizore.dao.impl.UserDAOImpl;
import com.mizore.entity.User;
import com.mizore.service.UserService;

public class UserServiceImpl implements UserService {

    //   持久层
    private UserDAO userDAO = new UserDAOImpl();


    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    public boolean login(User user) {
        User u = userDAO.getUser(user.getUsername());

        if (u != null) {
//            比对用户密码
            return u.getPassword().equals(user.getPassword());
        }
        return false;
    }
}
