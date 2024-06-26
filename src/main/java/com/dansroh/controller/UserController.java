package com.dansroh.controller;

import com.dansroh.pojo.Result;
import com.dansroh.pojo.User;
import com.dansroh.service.UserService;
import com.dansroh.utils.JwtUtil;
import com.dansroh.utils.Md5Util;
import com.dansroh.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");

        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result<String> update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("updateAvatar")
    public Result<String> updateAvatar(@RequestParam("avatarUrl") @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result<String> updatePwd(@RequestBody Map<String, String> params) {
        String oldPwd = params.get("oldPwd");
        String newPwd = params.get("newPwd");
        String rePwd = params.get("rePwd");
        // 校验参数
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }

        Map<String, Object> map = ThreadLocalUtil.get();
        User loginUser = userService.findByUserName(map.get("username").toString());
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码填写不正确");
        }

        if (!rePwd.equals(newPwd)) {
            return Result.error("两次填写的新密码不一致");
        }

        // 更新密码
        userService.updatePwd(newPwd);
        return Result.success();
    }
}
