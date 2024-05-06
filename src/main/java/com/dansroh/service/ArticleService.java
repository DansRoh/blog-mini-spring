package com.dansroh.service;

import com.dansroh.pojo.Article;
import com.dansroh.pojo.PageBean;

public interface ArticleService {

    // 创建文章
    void add(Article article);

    // 条件列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
