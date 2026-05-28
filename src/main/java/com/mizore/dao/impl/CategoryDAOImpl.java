package com.mizore.dao.impl;

import com.mizore.dao.CategoryDAO;
import com.mizore.dao.ClothesDAO;
import com.mizore.entity.Category;
import com.mizore.entity.Clothes;
import com.mizore.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {


    /**
     * 获取分类集合
     * @return
     */
    public List<Category> getCategories() {

        String sql = "select * from category";

        List<Category> categories = new ArrayList<>();

        try(Connection connection = DruidUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
                category.setSort(resultSet.getInt("sort"));
                category.setStatus(resultSet.getInt("status"));

                // 使用 getObject 并指定类型， 将数据的 DATETIME 映射为 Java Date 类型
                category.setCreateTime(resultSet.getTimestamp("create_time"));
                category.setUpdateTime(resultSet.getTimestamp("update_time"));
                categories.add(category);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return  categories;
    }


    /**
     * 修改分类
     * @param category
     * @return
     */
    public boolean modifyCategory(Category category) {

        String sql = "update category set name = ?, sort = ?, status = ?, update_time = ? where id = ?";

        try(
                Connection connection = DruidUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getSort());
            preparedStatement.setInt(3, category.getStatus());

            // 将LocalDateTime 映射为 TimeStamp
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setLong(5, category.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return (rowsAffected > 0);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
