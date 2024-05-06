package com.dansroh.service.impl;

import com.dansroh.mapper.ArticleMapper;
import com.dansroh.pojo.Article;
import com.dansroh.pojo.PageBean;
import com.dansroh.service.ArticleService;
import com.dansroh.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 创建PageBean对象
        PageBean<Article> pb = new PageBean<>();

        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        // 调用mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(categoryId, state, userId);

        Page<Article> page = (Page<Article>) as;

        pb.setTotal(page.getTotal());
        pb.setItems(page.getResult());
        return pb;
    }

    @Override
    public PageBean<Article> all(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 创建PageBean对象
        PageBean<Article> pb = new PageBean<>();

        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        // 调用mapper
        List<Article> as = articleMapper.all(categoryId, state);

        Page<Article> page = (Page<Article>) as;

        pb.setTotal(page.getTotal());
        pb.setItems(page.getResult());
        return pb;
    }
}
