package com.dansroh.controller;

import com.dansroh.pojo.Result;
import com.dansroh.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {

    @GetMapping("/list")
    public Result<String> list(@RequestHeader(name = "Authorization")String token, HttpServletResponse response) {
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
        } catch (Exception e) {
            // http响应状态码401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return Result.error("未登陆");
        }
        return Result.success();
    }
}
