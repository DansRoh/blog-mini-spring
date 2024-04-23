package com.dansroh.mapper;

import com.dansroh.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM category")
    List<Category> findAll();

    @Insert("insert into category(category_name, create_user_id, create_user_name, category_alias, create_time, update_time)" +
    " values(#{categoryName}, #{createUserId}, #{createUserName}, #{categoryAlias}, #{createTime}, #{updateTime})")
    void add(Category category);

    @Select("select * from category where category_name=#{categoryName}")
    Category findByCategoryName(String categoryName);

    @Delete("DELETE FROM category WHERE id = #{id}")
    int deleteIfFound(Integer id);

    @Update("update category set category_name=#{categoryName}, category_alias=#{categoryAlias}, update_time=#{updateTime} where id=#{id}")
    void update(Category category);

    @Select("select * from category where category_name=#{categoryName} and id <> #{id}")
    Category findByCategoryNameExcludeSelf(Category category);
}
