package com.mizore.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    /**
     * 生成 JWT Token
     *
     * @param secretKey  jwt秘钥 (建议至少32字节/256位)
     * @param ttlMillis  jwt过期时间(毫秒)
     * @param claims     自定义载荷信息
     * @return 加密后的token字符串
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // 1. 将字符串密钥转换为 SecretKey 对象 (新版强制要求)
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        // 2. 计算过期时间
        Date expiration = new Date(System.currentTimeMillis() + ttlMillis);

        // 3. 使用新版 Builder API 构建 Token
        return Jwts.builder()
                .claims(claims)           // 新版使用 claims() 替代 setClaims()
                .expiration(expiration)   // 新版使用 expiration() 替代 setExpiration()
                .signWith(key)            // 新版自动推断 HS256 算法，无需手动指定 SignatureAlgorithm
                .compact();
    }

    /**
     * 解析 JWT Token
     *
     * @param secretKey jwt秘钥
     * @param token     加密后的token
     * @return Claims 载荷信息
     */
    public static Claims parseJWT(String secretKey, String token) {
        // 1. 将字符串密钥转换为 SecretKey 对象
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        // 2. 使用新版 ParserBuilder 解析 (替代已删除的 Jwts.parser())
        return Jwts.parser()
                .verifyWith(key)          // 新版使用 verifyWith() 替代 setSigningKey()
                .build()
                .parseSignedClaims(token) // 新版使用 parseSignedClaims() 替代 parseClaimsJws()
                .getPayload();            // 新版使用 getPayload() 替代 getBody()
    }
}