package com.dansroh.service;

import com.dansroh.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    // 添加分类
    void add(Category category);

    // 根据分类名查找分类
    Category findByCategoryName(String categoryName);

    // 根据id删除分类
    Integer deleteIfFound(Integer id);

    void update(Category category);

    Category findByCategoryNameExcludeSelf(Category category);
}
