package com.cache.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cache.redis.CacheTestConfig;

public class CacheTest {

	public static void main(String[] arg){
		//@Configurationע���spring�������ط�ʽ����AnnotationConfigApplicationContext�滻ClassPathXmlApplicationContext
		ApplicationContext context = new AnnotationConfigApplicationContext(CacheTestConfig.class);
		
		 //�������spring_conf.xml�ļ���
		ApplicationContext applicationContext= new ClassPathXmlApplicationContext("xxxxxx");
	}
}
