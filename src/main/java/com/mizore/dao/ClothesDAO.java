package com.mizore.dao;

import com.mizore.entity.Clothes;

import java.util.List;

public interface ClothesDAO {


    /**
     * 获得商品数据
     * @return
     */
    List<Clothes> getList();
}
