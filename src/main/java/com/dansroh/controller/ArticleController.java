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
    public Result<String> list() {
        return Result.success("所有文章数据");
    }
}