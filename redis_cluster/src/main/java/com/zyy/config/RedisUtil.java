package com.zyy.config;

import org.springframework.stereotype.Service;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Service
public class RedisUtil {

	/**
	 * redis集群实例
	 **/
	private JedisCluster jedisCluster = null;

    /**
     * redis
     */
    private Jedis jedis = null;

	/**
	 * 资源文件读取实例
	 **/
	private Properties prop = new Properties();
	/**
	 * 初始化redis集群服务
	 * **/
	public void initRedisServer(){
		String redis_host_ip = this.getConfigValue("redis_host_ip");
		String redis_maxTotal = this.getConfigValue("redis_maxTotal");
		String redis_maxIdle = this.getConfigValue("redis_maxIdle");
		String redis_minIdle = this.getConfigValue("redis_minIdle");
		String redis_maxWaitMillis = this.getConfigValue("redis_maxWaitMillis");
		String minEvictableIdleTimeMillis = this.getConfigValue("redis_minEvictableIdleTimeMillis");
		String timeBetweenEvictionRunsMillis = this.getConfigValue("redis_timeBetweenEvictionRunsMillis");
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(this.isNotBlank(redis_maxTotal)?Integer.parseInt(redis_maxTotal):100);
		config.setMaxIdle(this.isNotBlank(redis_maxIdle)?Integer.parseInt(redis_maxIdle):50);
		config.setMinIdle(this.isNotBlank(redis_minIdle)?Integer.parseInt(redis_minIdle):20);
		config.setMaxWaitMillis(this.isNotBlank(redis_maxWaitMillis)?Integer.parseInt(redis_maxWaitMillis):6 * 1000);

		config.setBlockWhenExhausted(true);
		config.setTestOnBorrow(false);
		config.setTestOnReturn(false);
		config.setTestWhileIdle(true);
		config.setNumTestsPerEvictionRun(-1);
		config.setMinEvictableIdleTimeMillis(this.isNotBlank(minEvictableIdleTimeMillis)?Long.parseLong(minEvictableIdleTimeMillis):60000);
		config.setTimeBetweenEvictionRunsMillis(this.isNotBlank(timeBetweenEvictionRunsMillis)?Long.parseLong(timeBetweenEvictionRunsMillis):30000);
		config.setTestOnBorrow(true);
		// Redis集群的节点集合
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		if(this.isNotBlank(redis_host_ip)){
			String[] hostAndIPArray = redis_host_ip.split(";");
			for(String  hostAndIPSub : hostAndIPArray){
				String[] hostAndIP = hostAndIPSub.split(":");
				String ip = hostAndIP[0];
				String port = hostAndIP[1];
				jedisClusterNodes.add(new HostAndPort(ip, Integer.parseInt(port)));
			}
		}
		// 节点，超时时间，最多重定向次数，链接池
		jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 100, config);

	}
	/**
	 * 获取redis服务实例
	 * @return JedisCluster
	 * @throws Exception
	 */
	public JedisCluster getJedisCluster() throws Exception{
		return this.jedisCluster==null?createJedisCluster():this.jedisCluster;
	}
	
	/**
	 * 加载redis配置文件类
	 */
	public void loadRedisConfig() {
		try {
			InputStream ins = this.getClass().getClassLoader().getResourceAsStream("redis.properties");
			prop.load(ins);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 重新创建redis服务
	 * @return JedisCluster;
	 * @throws Exception
	 */
	@PostConstruct
	private JedisCluster createJedisCluster()throws Exception {
		//log.info("开始初始化redis......");
		loadRedisConfig();
		initRedisServer();
		return this.jedisCluster;  
	}
	/**
	 * 根据健名称取得配置文件的值
	 * @param key 健名称
	 * @return String 键值
	 */
	public String getConfigValue(String key) {
		String property = prop.getProperty(key);
		return property != null ? property.trim() : null;
	}
	/**
	 * 判断字符串是否为空
	 * @param str 字符串
	 * @return boolean 是否为空
	 */
	public boolean isNotBlank(String str){
		return (null != str && (!"".equals(str.trim())));
	}

}

