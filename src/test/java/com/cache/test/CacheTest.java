package com.cache.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cache.redis.CacheTestConfig;

public class CacheTest {

	public static void main(String[] arg){
		//@Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
		ApplicationContext context = new AnnotationConfigApplicationContext(CacheTestConfig.class);
		
		 //如果加载spring_conf.xml文件：
		ApplicationContext applicationContext= new ClassPathXmlApplicationContext("xxxxxx");
	}
}
