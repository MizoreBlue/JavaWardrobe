package com.mizore.dao.impl;

import com.mizore.dao.ClothesDAO;
import com.mizore.entity.Clothes;
import com.mizore.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClothesDAOImpl implements ClothesDAO {


    /**
     * 返会商品数据
     *
     * @return
     */
    public List<Clothes> getList() {
        List<Clothes> list = new ArrayList<>();
        String sql = "select * from clothes";
        try (Connection connection = DruidUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Clothes clothes = new Clothes();
                clothes.setId(resultSet.getLong("id"));
                clothes.setName(resultSet.getString("name"));
                clothes.setDescription(resultSet.getString("description"));
                clothes.setCategoryId(resultSet.getLong("category_id"));
                clothes.setImage(resultSet.getString("image"));
                clothes.setPrice(resultSet.getBigDecimal("price"));
                clothes.setStock(resultSet.getInt("stock"));
                clothes.setStyle(resultSet.getString("style"));
                list.add(clothes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 获取单个商品数据
     *
     * @param id
     * @return
     */
    public Clothes getSingleClothe(Integer id) {
        Clothes clothes = new Clothes();

        String sql = "select * from clothes where id = ?";

        try (Connection connection = DruidUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
          ) {
//            设置参数
            preparedStatement.setString(1, String.valueOf(id));
//            执行查询
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                clothes.setId(resultSet.getLong("id"));
                clothes.setName(resultSet.getString("name"));
                clothes.setDescription(resultSet.getString("description"));
                clothes.setCategoryId(resultSet.getLong("category_id"));
                clothes.setImage(resultSet.getString("image"));
                clothes.setPrice(resultSet.getBigDecimal("price"));
                clothes.setStock(resultSet.getInt("stock"));
                clothes.setStyle(resultSet.getString("style"));
                return clothes;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
