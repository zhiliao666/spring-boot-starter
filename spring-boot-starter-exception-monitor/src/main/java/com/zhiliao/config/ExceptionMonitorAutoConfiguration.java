package com.zhiliao.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zhiliao.handler.EmailMonitorExceptionProcess;
import com.zhiliao.handler.ExceptionMonitorInterceptor;
import com.zhiliao.handler.ExceptionMonitorPointcut;

@Configuration
@EnableConfigurationProperties(ExceptionMonitorProperties.class)
public class ExceptionMonitorAutoConfiguration {
	
	@Bean
	public ExceptionMonitorPointcut getExceptionMonitorPointcut(){
		return new ExceptionMonitorPointcut();
	}
	
	@Bean
	public ExceptionMonitorInterceptor getExceptionMonitorInterceptor(){
		return new ExceptionMonitorInterceptor();
	}
	/**
	 * 邮件发送bean
	 * @return
	 */
	@Bean
	public EmailMonitorExceptionProcess getEmailService(){
		return new EmailMonitorExceptionProcess();
	}
}
