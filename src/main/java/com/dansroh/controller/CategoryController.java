package com.dansroh.controller;

import com.dansroh.pojo.Category;
import com.dansroh.pojo.Result;
import com.dansroh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 获取所有分类列表
    @GetMapping("/list")
    public Result<List<Category>> list() {
        List<Category> allCategory = categoryService.findAll();
        return Result.success(allCategory);
    }

    // 添加分类
    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated Category category) {

        // 查询是否有相同的分类名
        Category category1 = categoryService.findByCategoryName(category.getCategoryName());
        if (category1 != null) {
            return Result.error("分类名已经存在");
        }

        categoryService.add(category);
        return Result.success();
    }

    // 删除分类
    @DeleteMapping("/delete")
    public Result<String> delete(@RequestParam Integer id) {
        try {
            int deletedRows = categoryService.deleteIfFound(id);
            if (deletedRows == 0) {
                // 没有找到
                return Result.error("分类id不存在");
            }
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
