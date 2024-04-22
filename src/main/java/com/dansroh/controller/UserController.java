package com.dansroh.controller;

import com.dansroh.pojo.Result;
import com.dansroh.pojo.User;
import com.dansroh.service.UserService;
import com.dansroh.utils.JwtUtil;
import com.dansroh.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<String> register(@Pattern(regexp = "^\\S{5,16}$")String username, @Pattern(regexp = "^\\S{5,16}$")String password) {
        User u = userService.findByUserName(username);
        if (u == null) {
            // 没有占用
            userService.register(username, password);
            return Result.success();
        } else {
            // 占用
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$")String username, @Pattern(regexp = "^\\S{5,16}$")String password) {
        User u = userService.findByUserName(username);
        if (u == null) {
            return Result.error("用户名错误");
        }
        if (Md5Util.getMD5String(password).equals(u.getPassword())) {
            // 登陆成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", username);
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }
}
