package com.exfo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * description redis Service
 *
 * @author zhengyueyan
 * @date 16:19 2018/8/28
 */
@Service
public class RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    RedisPool redisPool;

    /**
     * 设置key的有效期，单位是秒
     *
     * @param key
     * @param exTime
     * @return
     */
    public Long expire(String key, int exTime) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = redisPool.getJedis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("expire key:{} error", key, e);
            return result;
        }
        return result;
    }

    //exTime的单位是秒
    public String setEx(String key, String value, int exTime) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = redisPool.getJedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("setex key:{} value:{} error", key, value, e);
            return result;
        }
        return result;
    }

    public String set(String key, String value) {
        Jedis jedis = null;
        String result = null;

        try {
            jedis = redisPool.getJedis();
            result = jedis.setex(key,Constant.REDIS_EXPIRE,value );
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key, value, e);
            return result;
        }
        return result;
    }

    public String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = redisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            return result;
        }
        return result;
    }

    public boolean exist(String key){
        Jedis jedis = null;
        boolean result = false;
        try {
            jedis = redisPool.getJedis();
            result = jedis.exists(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            return result;
        }
        return result;
    }

    public Long del(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = redisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error", key, e);
            return result;
        }
        return result;
    }

}
