package com.mizore.service;

import com.mizore.entity.Clothes;

import java.util.List;

public interface ClothesService {


    /**
     * 查询商品数据
     * @return
     */
    List<Clothes> getClothesList();


    /**
     * 获取单个商品数据
     * @return
     */
    Clothes getClothesDetails(Integer id);
}
