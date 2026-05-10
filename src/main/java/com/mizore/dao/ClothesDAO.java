package com.mizore.dao;

import com.mizore.entity.Clothes;

import java.util.List;

public interface ClothesDAO {


    /**
     * 获得商品数据
     * @return
     */
    List<Clothes> getList();


    /**
     * 获取单个商品数据
     * @param id
     * @return
     */
    Clothes getSingleClothe(Integer id);
}
