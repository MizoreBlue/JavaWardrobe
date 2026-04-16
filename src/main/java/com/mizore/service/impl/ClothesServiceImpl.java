package com.mizore.service.impl;

import com.mizore.dao.ClothesDAO;
import com.mizore.dao.impl.ClothesDAOImpl;
import com.mizore.entity.Clothes;
import com.mizore.service.ClothesService;

import java.util.List;

public class ClothesServiceImpl implements ClothesService {

    private ClothesDAO clothesDAO = new ClothesDAOImpl();

    /**
     * 获取商品数据
     * @return
     */
    public List<Clothes> getClothesList() {
        return clothesDAO.getList();
    }
}
