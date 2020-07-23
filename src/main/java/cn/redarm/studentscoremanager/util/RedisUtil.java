package cn.redarm.studentscoremanager.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author redarm
 * @Date 2020/6/19 12:57 下午
 **/
@Component
public class RedisUtil {

    @Value("${redis.key.expireTime}")
    private Long expireTime;

    @Autowired
    private StringRedisTemplate template;

    /**
     * @return void
     * @Author redarm
     * @Description //TODO add a value to redis and set timeout is expireTime
     * @Date 1:08 下午 2020/6/19
     * @Param [k, v]
     **/
    public void add(String k, String v) {
        template.opsForValue().set(k, v);
        this.setExpireTime(k, expireTime);
    }

    public String get(String k){
        return template.opsForValue().get(k);
    }

    /**
     * @return java.lang.String
     * @Author redarm
     * @Description //TODO remove a value from redis
     * @Date 1:08 下午 2020/6/19
     * @Param [k]
     **/
    public String remove(String k) {
        return template.opsForValue().get(k);
    }

    public void setExpireTime(String k, Long expireTime) {
        template.expire(k, expireTime, TimeUnit.SECONDS);
    }
}
