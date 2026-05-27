package com.mizore.dao;

import com.mizore.entity.Category;
import com.mizore.entity.Clothes;

import java.util.List;

public interface CategoryDAO {


    /**
     * 获取分类集合
     * @return
     */
    List<Category> getCategories();


    /**
     * 修改分类
     * @param category
     * @return
     */
    boolean modifyCategory(Category category);
}
