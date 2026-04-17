package com.mizore.dao.impl;

import com.mizore.dao.UserDAO;
import com.mizore.entity.User;
import com.mizore.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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


    /**
     * 添加一个用户
     *
     * @param user
     */
    public void insert(User user) {
        String sql = "INSERT INTO user (username, password, phone, email, sex, avatar, create_time) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DruidUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

//            设置参数
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getSex());
            preparedStatement.setString(6, user.getAvatar());

            // 处理时间类型：java.time.LocalDateTime 转 java.sql.Timestamp
            if (user.getCreateTime() != null) {
                preparedStatement.setTimestamp(7, Timestamp.valueOf(user.getCreateTime()));
            } else {
                preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now())); // 如果没传时间，默认当前时间
            }

            // 4. 执行 SQL
            // executeUpdate 返回的是受影响的行数（int）
            int rows = preparedStatement.executeUpdate();


            if (rows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
            }

        } catch (Exception e) {
//            打印错误堆栈信息
            e.printStackTrace();
        }
    }
}
