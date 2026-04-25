package com.mizore.service.impl;

import com.mizore.dao.UserDAO;
import com.mizore.dao.impl.UserDAOImpl;
import com.mizore.entity.User;
import com.mizore.service.UserService;

import java.util.List;
import java.util.Objects;

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


    /**
     * 用户注册
     *
     * @param user
     */
    public boolean register(User user) {
//        调用DAO 层
//        暂且要求用户名唯一
        User user1 = userDAO.getUser(user.getUsername());
        if (user1.getUsername() != null) {
//            用户已存在，回显错误信息。。。
            return false;
        } else {
            userDAO.insert(user);
            return true;
        }
    }


    /**
     * 获取所哟用户
     *
     * @return
     */
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }
}
