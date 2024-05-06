package com.dansroh.controller;

import com.dansroh.pojo.Article;
import com.dansroh.pojo.PageBean;
import com.dansroh.pojo.Result;
import com.dansroh.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 条件查询当前登陆用户下的文章
    @GetMapping()
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pb);
    }

    // 新增文章
    @PostMapping()
    public Result<String> add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }
}
