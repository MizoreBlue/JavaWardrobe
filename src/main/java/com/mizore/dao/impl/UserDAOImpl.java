package com.mizore.dao.impl;

import com.mizore.dao.UserDAO;
import com.mizore.entity.User;
import com.mizore.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpl implements UserDAO {


    /**
     * 用户登录
     *
     * @param username
     * @return
     */
    public User getUser(String username) {
        User user = new User();
        String sql = "select * from user where username=?";
        try (Connection connection = DruidUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            //            设置参数
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
