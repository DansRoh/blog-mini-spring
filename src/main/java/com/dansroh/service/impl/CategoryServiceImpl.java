package com.dansroh.service.impl;

import com.dansroh.mapper.CategoryMapper;
import com.dansroh.pojo.Category;
import com.dansroh.service.CategoryService;
import com.dansroh.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    // 获取所有分类信息
    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    // 添加分类
    @Override
    public void add(Category category) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        String username = (String) map.get("username");

        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUserName(username);
        category.setCreateUserId(userId);

        categoryMapper.add(category);
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return categoryMapper.findByCategoryName(categoryName);
    }

    @Override
    public Integer deleteIfFound(Integer id) {
        return categoryMapper.deleteIfFound(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public Category findByCategoryNameExcludeSelf(Category category) {
        return categoryMapper.findByCategoryNameExcludeSelf(category);
    }
}
