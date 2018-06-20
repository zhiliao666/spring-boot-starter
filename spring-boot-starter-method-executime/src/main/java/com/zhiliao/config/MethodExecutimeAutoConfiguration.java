package com.zhiliao.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zhiliao.handler.MethodExecutimePointcut;

@Configuration
@EnableConfigurationProperties(MethodExecutimeProperties.class)
public class MethodExecutimeAutoConfiguration {
	
	@Bean
	public MethodExecutimePointcut getMethodExecutimePointcut(){
		System.out.println("getMethodExecutimePointcut---------");
		return new MethodExecutimePointcut();
	}
}
