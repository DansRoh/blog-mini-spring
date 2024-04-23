package com.dansroh.mapper;

import com.dansroh.pojo.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM category")
    List<Category> findAll();

    @Insert("insert into category(category_name, create_user_id, create_user_name, category_alias, create_time, update_time)" +
    " values(#{categoryName}, #{createUserId}, #{createUserName}, #{categoryAlias}, now(), now())")
    void add(String categoryName, Integer createUserId, String createUserName, String categoryAlias);

    @Select("select * from category where category_name=#{categoryName}")
    Category findByCategoryName(String categoryName);

    @Delete("DELETE FROM category WHERE id = #{id}")
    int deleteIfFound(Integer id);
}
