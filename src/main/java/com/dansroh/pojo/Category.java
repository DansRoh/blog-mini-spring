package com.dansroh.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    private Integer id;//主键ID
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String categoryName;//分类名称
    private String categoryAlias;//分类别名
    private Integer createUserId;//创建人ID
    private String createUserName;//创建人username
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
