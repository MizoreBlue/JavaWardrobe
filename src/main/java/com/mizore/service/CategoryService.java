package com.mizore.service;

import com.mizore.entity.Category;
import com.mizore.entity.Clothes;

import java.util.List;

public interface CategoryService {


    /**
     * 获取分类集合
     * @return
     */
    List<Category> getCategoryList();


    /**
     * 修改分类
     * @param category
     * @return
     */
    boolean updateCategory(Category category);
}
