package com.cache.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis 缓存 + Spring 的集成示例。
 * 
 * 要启用缓存支持，我们需要创建一个新的 CacheManager bean。
 * CacheManager 接口有很多实现，本文演示的是和 Redis 的集成，
 * 自然就是用 RedisCacheManager 了。Redis 不是应用的共享内存，它只是一个内存服务器，就像 MySql 似的，
 * 我们需要将应用连接到它并使用某种“语言”进行交互，因此我们还需要一个连接工厂以及一个 Spring 和 Redis 对话要用的 RedisTemplate，
 * 这些都是 Redis 缓存所必需的配置，把它们都放在自定义的 CachingConfigurerSupport 
 * <p>Title:RedisCacheConfig</p>
 * @author liuwanlin
 * @date 2017年12月22日下午4:55:31
 */
@Configuration 
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport{
	
	/** 
	 * spring集成Redis的几个步骤：  
     1、搭建Redis 服务器环境（windows环境下搭建\Linux环境下搭建）  
     2、启动Redis服务  
     3、maven项目中添加依赖  
     4、配置Spring对Redis相关bean的引用  
     5、配置Redis基本属性  
     6、编辑实现RedisTemplate的实现工具类  
     7、遇到的问题  
    */
	
	@Bean /**@Bean标注在方法上(返回某个实例的方法)，等价于spring的xml配置文件中的<bean>，作用为：注册bean对象*/
	public JedisConnectionFactory  redisConnectionFactory(){
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		
		connectionFactory.setHostName("127.0.0.1");
		connectionFactory.setPort(6379);
		return connectionFactory;
	} 

	//@Bean注解注册bean,同时可以指定初始化和销毁方法
    //@Bean(name="testNean",initMethod="start",destroyMethod="cleanUp")
	@Bean   
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf){
		RedisTemplate<String, String> redisTemplate=new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate){
		RedisCacheManager cacheManager=new RedisCacheManager(redisTemplate);
		// 如果这里报错 则是 spring 包 引入不正确造成
		// 错误  The type org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager cannot be resolved. It is indirectly referenced from required .class fi
		cacheManager.setDefaultExpiration(3000);
		return cacheManager;
	}
	
	
	/**
	 *  (1)、@Bean注解在返回实例的方法上，如果未通过@Bean指定bean的名称，则默认与标注的方法名相同；
	 *  (2)、@Bean注解默认作用域为单例singleton作用域，可通过@Scope(“prototype”)设置为原型作用域；
	 *  (3)、既然@Bean的作用是注册bean对象，那么完全可以使用@Component、@Controller、@Service、
	 *    @Ripository等注解注册bean，当然需要配置@ComponentScan注解进行自动扫描。
	 */
}
