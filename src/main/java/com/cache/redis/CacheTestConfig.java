package com.cache.redis;

import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 相当于把该类作为spring的xml配置文件中的<beans>，作用为：配置spring容器(应用上下文)
 * //添加自动扫描注解，basePackages为TestBean包路径
 * @ComponentScan(basePackages = "com.test.spring.support.configuration")
 * //添加注册bean的注解
 * @Component
 * <p>Title:CacheTestConfig</p>
 * @author liuwanlin
 * @date 2017年12月22日下午5:59:34
 */
@Configuration
public class CacheTestConfig {

	public CacheTestConfig(){
		System.out.println("初始化。。。");
	}
}
