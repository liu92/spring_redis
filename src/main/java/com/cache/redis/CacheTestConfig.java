package com.cache.redis;

import org.springframework.context.annotation.Configuration;

/**
 * @Configuration �൱�ڰѸ�����Ϊspring��xml�����ļ��е�<beans>������Ϊ������spring����(Ӧ��������)
 * //����Զ�ɨ��ע�⣬basePackagesΪTestBean��·��
 * @ComponentScan(basePackages = "com.test.spring.support.configuration")
 * //���ע��bean��ע��
 * @Component
 * <p>Title:CacheTestConfig</p>
 * @author liuwanlin
 * @date 2017��12��22������5:59:34
 */
@Configuration
public class CacheTestConfig {

	public CacheTestConfig(){
		System.out.println("��ʼ��������");
	}
}
