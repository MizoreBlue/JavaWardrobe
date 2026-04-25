package com.mizore.dao.impl;

import com.mizore.dao.UserDAO;
import com.mizore.entity.User;
import com.mizore.utils.DruidUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    /**
     * 添加一个用户
     *
     * @param user
     */
    public void insert(User user) {
        String sql = "INSERT INTO user (username, password, phone, email, sex, avatar, role,create_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DruidUtils.getConnection();
             // 关键修改在这里：添加 Statement.RETURN_GENERATED_KEYS
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

//            设置参数
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getSex());
            preparedStatement.setString(6, user.getAvatar());
            preparedStatement.setLong(7, user.getRole());

            // 处理时间类型：java.time.LocalDateTime 转 java.sql.Timestamp
            if (user.getCreateTime() != null) {
                preparedStatement.setTimestamp(8, Timestamp.valueOf(user.getCreateTime()));
            } else {
                preparedStatement.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now())); // 如果没传时间，默认当前时间
            }

            // 4. 执行 SQL
            int rows = preparedStatement.executeUpdate();

            // 5. 获取生成的主键 ID
            if (rows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有用户
     *
     * @return
     */
    public List<User> getAllUser() {
        String sql = "select * from user";
        List<User> users = new ArrayList<>();
        try (Connection connection = DruidUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setAvatar(resultSet.getString("avatar"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getLong("role"));
                user.setSex(resultSet.getString("sex"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
