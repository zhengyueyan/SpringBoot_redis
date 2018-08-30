package com.exfo.common;

import java.text.SimpleDateFormat;

/**
 * 常量类
 */
public class Constant {

    /**
     * redis 域名
     */
    public static final String REDIS_HOST = "127.0.0.1";

    /**
     * redis 端口
     */
    public static final int REDIS_PORT = 6379;

    /**
     * redis 配置缓存过期时间
     */
    public static final int REDIS_EXPIRE = 1800;

    /**
     * redis 密码
     */
    public static final String REDIS_PASS = "123456";

    /**
     * redis 最大连接数
     */
    public static final Integer REDIS_MAX_TOTLE = 20;

    /**
     * redis 在jedispool中最大的idle状态(空闲的)的jedis实例的个数
     */
    public static final Integer REDIS_MAX_IDLE = 20;

    /**
     * redis 在jedispool中最小的idle状态(空闲的)的jedis实例的个数
     */
    public static final Integer REDIS_MIN_IDLE = 20;

    /**
     * redis 在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值true。则得到的jedis实例肯定是可以用的。
     */
    public static final Boolean REDIS_TEST_ON_BORROW = true;

    /**
     * redis 在return一个jedis实例的时候，是否要进行验证操作，如果赋值true。则放回jedispool的jedis实例肯定是可以用的。
     */
    public static final Boolean REDIS_TEST_ON_RETURN = true;


}
