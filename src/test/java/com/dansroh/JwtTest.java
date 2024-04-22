package com.dansroh;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void shouldGenerateToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");
        // 生成jwt的代码
        String token = JWT.create()
                .withClaim("user", claims) // 添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*60*12)) // 添加过期时间
                .sign(Algorithm.HMAC256("dansroh")); // 指定算法

        System.out.println(token);
    }
}
