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
 * Redis ���� + Spring �ļ���ʾ����
 * 
 * Ҫ���û���֧�֣�������Ҫ����һ���µ� CacheManager bean��
 * CacheManager �ӿ��кܶ�ʵ�֣�������ʾ���Ǻ� Redis �ļ��ɣ�
 * ��Ȼ������ RedisCacheManager �ˡ�Redis ����Ӧ�õĹ����ڴ棬��ֻ��һ���ڴ������������ MySql �Ƶģ�
 * ������Ҫ��Ӧ�����ӵ�����ʹ��ĳ�֡����ԡ����н�����������ǻ���Ҫһ�����ӹ����Լ�һ�� Spring �� Redis �Ի�Ҫ�õ� RedisTemplate��
 * ��Щ���� Redis ��������������ã������Ƕ������Զ���� CachingConfigurerSupport 
 * <p>Title:RedisCacheConfig</p>
 * @author liuwanlin
 * @date 2017��12��22������4:55:31
 */
@Configuration 
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport{
	
	/** 
	 * spring����Redis�ļ������裺  
     1���Redis ������������windows�����´\Linux�����´��  
     2������Redis����  
     3��maven��Ŀ���������  
     4������Spring��Redis���bean������  
     5������Redis��������  
     6���༭ʵ��RedisTemplate��ʵ�ֹ�����  
     7������������  
    */
	
	@Bean /**@Bean��ע�ڷ�����(����ĳ��ʵ���ķ���)���ȼ���spring��xml�����ļ��е�<bean>������Ϊ��ע��bean����*/
	public JedisConnectionFactory  redisConnectionFactory(){
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		
		connectionFactory.setHostName("127.0.0.1");
		connectionFactory.setPort(6379);
		return connectionFactory;
	} 

	//@Beanע��ע��bean,ͬʱ����ָ����ʼ�������ٷ���
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
		// ������ﱨ�� ���� spring �� ���벻��ȷ���
		// ����  The type org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager cannot be resolved. It is indirectly referenced from required .class fi
		cacheManager.setDefaultExpiration(3000);
		return cacheManager;
	}
	
	
	/**
	 *  (1)��@Beanע���ڷ���ʵ���ķ����ϣ����δͨ��@Beanָ��bean�����ƣ���Ĭ�����ע�ķ�������ͬ��
	 *  (2)��@Beanע��Ĭ��������Ϊ����singleton�����򣬿�ͨ��@Scope(��prototype��)����Ϊԭ��������
	 *  (3)����Ȼ@Bean��������ע��bean������ô��ȫ����ʹ��@Component��@Controller��@Service��
	 *    @Ripository��ע��ע��bean����Ȼ��Ҫ����@ComponentScanע������Զ�ɨ�衣
	 */
}
