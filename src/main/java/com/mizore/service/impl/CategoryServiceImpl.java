package com.mizore.service.impl;

import com.mizore.dao.CategoryDAO;
import com.mizore.dao.ClothesDAO;
import com.mizore.dao.impl.CategoryDAOImpl;
import com.mizore.dao.impl.ClothesDAOImpl;
import com.mizore.entity.Category;
import com.mizore.entity.Clothes;
import com.mizore.service.CategoryService;
import com.mizore.service.ClothesService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    /**
     * 获取分类集合
     * @return
     */
    public List<Category> getCategoryList() {
        return categoryDAO.getCategories();
    }


    /**
     * 修改分类
     * @param category
     * @return
     */
    public boolean updateCategory(Category category) {
        return categoryDAO.modifyCategory(category);
    }
}
