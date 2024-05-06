package com.dansroh.service;

import com.dansroh.pojo.Article;
import com.dansroh.pojo.PageBean;

public interface ArticleService {

    // 创建文章
    void add(Article article);

    // 条件查询当前登陆用户下的文章
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    // 条件查询所有文章
    PageBean<Article> all(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
