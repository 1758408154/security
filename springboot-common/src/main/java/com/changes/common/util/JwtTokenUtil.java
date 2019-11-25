package com.changes.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuJunJie
 * @since 2019/11/20 16:15
 */
@Slf4j
public class JwtTokenUtil {


    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_CREATED = "created";

    private String secret = "tepia";

    private Long expiration = 60 * 60 * 24 * 30L;

    private String tokenHead;


    /**
     * 根据负责生成JWT的token
     *
     * @param claims
     * @return java.lang.String
     * @author LiuJunJie
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    /**
     * 生成过期时间
     *
     * @param
     * @return java.util.Date
     * @author LiuJunJie
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 获得过期时间
     *
     * @param token
     * @return java.util.Date
     * @author LiuJunJie
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    private Claims getClaimsFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public String getUserNameFromToken(String token) {
        String username;
        Claims claims = getClaimsFromToken(token);
        username = claims.getSubject();
        return username;
    }

    /**
     * 根据用户信息生成token
     *
     * @param userDetails
     * @return java.lang.String
     * @author LiuJunJie
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


    /***
     * 判断token是否失效
     * @param  token
     * @return boolean
     * @author LiuJunJie
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 验证token是否有效
     * @param  token
     * @param userDetails
     * @return boolean
     * @author LiuJunJie
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
