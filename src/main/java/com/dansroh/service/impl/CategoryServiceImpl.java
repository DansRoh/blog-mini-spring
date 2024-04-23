package com.dansroh.service.impl;

import com.dansroh.mapper.CategoryMapper;
import com.dansroh.pojo.Category;
import com.dansroh.service.CategoryService;
import com.dansroh.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Integer id = (Integer) map.get("id");
        String username = (String) map.get("username");
        String categoryName = category.getCategoryName();
        String categoryAlias = category.getCategoryAlias();


        categoryMapper.add(categoryName, id, username, categoryAlias);
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return categoryMapper.findByCategoryName(categoryName);
    }

    @Override
    public Integer deleteIfFound(Integer id) {
        return categoryMapper.deleteIfFound(id);
    }
}
