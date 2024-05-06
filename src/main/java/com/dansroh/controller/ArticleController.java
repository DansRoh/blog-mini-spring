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

    @PostMapping()
    public Result<String> add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }
}
