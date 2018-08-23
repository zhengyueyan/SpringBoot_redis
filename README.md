# Springboot-Redis
redis测试demo

# redis集群模式工具类添加
 RedisService  <Br/>
 RedisUtil  <Br/>
 注意：该工具类使用的是redis集群，非集群不能使用  <Br/>
 该工具配置了 节点，超时时间，最多重定向次数，链接池  <Br/>
 redis集群不能设置密码，否则抛出(error) NOAUTH Authentication required 异常  <Br/>
  
# 使用 
 直接使用注解注入进service即可直接使用  <Br/>
 @Autowired  <Br/>
 private RedisService redisService;