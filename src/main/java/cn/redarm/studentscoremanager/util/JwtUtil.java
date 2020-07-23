package cn.redarm.studentscoremanager.util;

import cn.redarm.studentscoremanager.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author redarm
 * @Date 2020/6/19 3:43 下午
 **/
@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expireTime}")
    private Long expireTime;

    private static final String USER_NAME = "sub";

    private static final String CREATED_TIME = "created";

    public static final String TOKEN_HEADER = "auth";

    public static final String TOKEN_PREFIX = "Bearer";

    /**
     * @return java.lang.String
     * @Author redarm
     * @Description //TODO create token from user
     * @Date 3:50 下午 2020/6/19
     * @Param [user]
     **/
    public String createToken(User user) {
        String token = null;

        Map<String, Object> claims = new HashMap<>();

        claims.put(USER_NAME, user.getUsername());
        claims.put(CREATED_TIME, new Date());

        try {
            token = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + expireTime * 1000))
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
        } catch (Exception e) {
            log.warn("user: {} create token error : ", user.getUsername(), e);
        }

        return token;
    }

    /**
     * @return io.jsonwebtoken.Claims
     * @Author redarm
     * @Description //TODO get claims from token
     * @Date 3:51 下午 2020/6/19
     * @Param [token]
     **/
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;

        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.warn("get claims from token error" + e);
        }

        return claims;
    }

    /**
     * @return java.lang.Boolean
     * @Author redarm
     * @Description //TODO judge if the token has expired
     * @Date 3:52 下午 2020/6/19
     * @Param [token]
     **/
    public Boolean tokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }

    /**
     * @return java.lang.String
     * @Author redarm
     * @Description //TODO get username form token
     * @Date 3:53 下午 2020/6/19
     * @Param [token]
     **/
    public String getUsernameFromToken(String token) {
        String username = null;

        try {
            username = getClaimsFromToken(token).getSubject();
        } catch (Exception e) {
            log.warn("get username from token error" + e);
        }

        return username;
    }
}
