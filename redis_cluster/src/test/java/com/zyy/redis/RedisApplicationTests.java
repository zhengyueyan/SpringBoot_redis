package com.zyy.redis;

import com.zyy.config.RedisService;
import com.zyy.config.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    /**
     * 日志对象
     * **/
    private static final Logger fileLog = LoggerFactory.getLogger(RedisApplicationTests.class);

    /**
     * 测试删除redis缓存String<br>
     * @throws Exception
     * **/
    @Test
    public void testRedisCachedStrService() throws Exception {
        RedisService redisServiceUtil = new RedisService();
        RedisUtil redisUtil = new RedisUtil();
        redisServiceUtil.redisUtil = redisUtil;
        try {
            String cacheKey = "testStr";
            redisServiceUtil.cacheString(cacheKey, "平步青云");
            String targetStr = redisServiceUtil.getCacheString(cacheKey);
            System.out.println(targetStr);
        } catch (Exception e) {
            e.printStackTrace();
            fileLog.error("测试删除redis缓存对象异常:"+e.getMessage());
        }
    }
    /**
     * 测试redis缓存指定时间后失效<br>
     * @throws Exception
     * **/
    @Test
    public void testRedisExpireCacheObjService() throws Exception {
        RedisService redisServiceUtil = new RedisService();
        RedisUtil redisUtil = new RedisUtil();
        redisServiceUtil.redisUtil = redisUtil;
        try {
            String cacheKey = "testStr";
            redisServiceUtil.cacheString(cacheKey, "平步青云");
            redisServiceUtil.expireCacheObj(cacheKey, 4);
            String targetStr = redisServiceUtil.getCacheString(cacheKey);
            System.out.println(targetStr);
            Thread.sleep(5000l);
            targetStr = redisServiceUtil.getCacheString(cacheKey);
            System.out.println(targetStr);
        } catch (Exception e) {
            e.printStackTrace();
            fileLog.error("测试删除redis缓存对象异常:"+e.getMessage());
        }
    }
    /**
     * 测试缓存HashMap对象<br>
     * @throws Exception
     * **/
    @Test
    public void testRedisCachedMapService() throws Exception {
        RedisService redisServiceUtil = new RedisService();
        RedisUtil redisUtil = new RedisUtil();
        redisServiceUtil.redisUtil = redisUtil;
        Map<String,String> targetMap = new HashMap<String,String>();
        targetMap.put("userName", "刀郎");
        try {
            String cacheKey = "testMap";
            redisServiceUtil.cacheMap(cacheKey, targetMap);
            Map<String,String> cachedMap = redisServiceUtil.getCachedMap(cacheKey);
            for(String key : cachedMap.keySet()){
                System.out.println(cachedMap.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fileLog.error("测试缓存HashMap对象异常:"+e.getMessage());
        }
    }
    /**
     * 测试缓存List对象<br>
     * @throws Exception
     * **/
    @Test
    public void testRedisCachedListService() throws Exception {
        RedisService redisServiceUtil = new RedisService();
        RedisUtil redisUtil = new RedisUtil();
        redisServiceUtil.redisUtil = redisUtil;
        List<String> targetList = new ArrayList<String>();
        targetList.add("userName");
        targetList.add("刀郎");
        try {
            String cacheKey = "testList";
            redisServiceUtil.cacheList(cacheKey, targetList);
            List<String> testList = redisServiceUtil.getCachedList(cacheKey);
            System.out.println(testList.size());
            for(String listValue : testList){
                System.out.println(listValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fileLog.error("测试缓存List对象异常:"+e.getMessage());
        }
    }
    /**
     * 测试删除redis缓存对象<br>
     * @throws Exception
     * **/
    @Test
    public void testRedisDelCachedObjService() throws Exception {
        RedisService redisServiceUtil = new RedisService();
        RedisUtil redisUtil = new RedisUtil();
        redisServiceUtil.redisUtil = redisUtil;
        try {
            redisServiceUtil.delCachedObj("testMap");
            redisServiceUtil.delCachedObj("testList");
        } catch (Exception e) {
            e.printStackTrace();
            fileLog.error("测试删除redis缓存对象异常:"+e.getMessage());
        }
    }
	
}
