package com.dansroh.service;

import com.dansroh.pojo.User;

public interface UserService {
    // 根据用户名查询用户
    User findByUserName(String username);

    void register(String username, String password);

    void update(User user);
}
