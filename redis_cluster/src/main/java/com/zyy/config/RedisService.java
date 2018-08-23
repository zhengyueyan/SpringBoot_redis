package com.zyy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RedisService {
	/**
	 * redis集群服务实例
	 * **/
	@Autowired
	public RedisUtil redisUtil;

	/**
	 * 缓存String对象
	 * @param cacheKey 缓存KEY
	 * @param value key对应的字符串
	 * @throws Exception
	 * **/
    public void cacheString(String cacheKey,String value) throws Exception{
    	redisUtil.getJedisCluster().set(cacheKey, value);
    }
    /**
	 * 获取缓存的String对象
	 * @param cacheKey 缓存KEY
	 * @return String 缓存的实际字符串值
	 * @throws Exception
	 * **/
    public String getCacheString(String cacheKey) throws Exception{
    	return redisUtil.getJedisCluster().get(cacheKey);
    }
    /**
	 * 为缓存对象设置缓存时间
	 * @param cacheKey 缓存KEY
	 * @param seconds 缓存的秒数
	 * @throws Exception
	 * **/
    public void expireCacheObj(String cacheKey,int seconds) throws Exception{
    	redisUtil.getJedisCluster().expire(cacheKey, seconds);
    }
	/**
	 * 缓存HashMap对象
	 * @param cacheKey 缓存KEY
	 * @param targetMap 需要缓存的MAP对象
	 * @throws Exception
	 * **/
    public void cacheMap(String cacheKey,Map<String,String> targetMap) throws Exception{
    	redisUtil.getJedisCluster().hmset(cacheKey,targetMap);
    }
    
    /**
	 * 从缓存中获取HashMap对象
	 * @param cacheKey 缓存KEY
	 * @return Map<String,String> 缓存的map对象
	 * @throws Exception
	 * **/
    public Map<String,String> getCachedMap(String cacheKey) throws Exception{
		return redisUtil.getJedisCluster().hgetAll(cacheKey);
    }
    
    /**
	 * 缓存List对象
	 * @param cacheKey 缓存KEY
	 * @param targetList 需要缓存的List对象
	 * @throws Exception
	 * **/
    public void cacheList(String cacheKey,List<String> targetList) throws Exception{
    	for(String value : targetList){
    		redisUtil.getJedisCluster().lpush(cacheKey,value);
    	}
    }
    /**
	 * 从缓存中获取List对象
	 * @param cacheKey 缓存KEY
	 * @return List<String> 缓存的List对象
	 * @throws Exception
	 * **/
    public List<String> getCachedList(String cacheKey) throws Exception{
    	return redisUtil.getJedisCluster().lrange(cacheKey,0, -1);
    }
    
    /**
	 * 根据key删除缓存中的对象
	 * @param cacheKey 缓存KEY
	 * @throws Exception
	 * **/
    public void delCachedObj(String cacheKey) throws Exception{
    	redisUtil.getJedisCluster().del(cacheKey);
    }
    
}

