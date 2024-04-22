package com.dansroh.service;

import com.dansroh.pojo.User;

public interface UserService {
    // 根据用户名查询用户
    User findByUserName(String username);

    // 注册用户
    void register(String username, String password);

    // 更新用户基本信息
    void update(User user);

    // 更新头像
    void updateAvatar(String avatarUrl);
}
