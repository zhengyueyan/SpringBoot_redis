package com.exfo.common;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * description redis 连接池
 *
 * @author zhengyueyan
 * @date 17:18 2018/8/28
 */
@Service
public class RedisPool {

    private JedisPool pool;//jedis连接池

    private void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(Constant.REDIS_MAX_TOTLE);
        config.setMaxIdle(Constant.REDIS_MAX_IDLE);
        config.setMinIdle(Constant.REDIS_MIN_IDLE);

        config.setTestOnBorrow(Constant.REDIS_TEST_ON_BORROW);
        config.setTestOnReturn(Constant.REDIS_TEST_ON_RETURN);

        config.setBlockWhenExhausted(true);//连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。

        pool = new JedisPool(config,Constant.REDIS_HOST,Constant.REDIS_PORT,Constant.REDIS_EXPIRE,Constant.REDIS_PASS);
    }

    @PostConstruct
    private JedisPool createJedisPool()throws Exception {
        initPool();
        return this.pool;
    }

    /**
     * 获取redis服务实例
     * @return Jedis
     * @throws Exception
     */
    public Jedis getJedis() throws Exception{
        return pool==null?createJedisPool().getResource():pool.getResource();
    }

}
