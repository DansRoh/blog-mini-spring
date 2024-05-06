package com.dansroh.mapper;

import com.dansroh.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper()
public interface ArticleMapper {
    // 新增
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time)" +
    " values (#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, now(), now())")
    void add(Article article);

    // 查询当前登陆用户下的文章
    List<Article> list(Integer categoryId, String state, Integer userId);

    // 查询所有文章
    List<Article> all(Integer categoryId, String state);
}
