package com.zhiliao.handler;

import javax.annotation.PostConstruct;

import org.aopalliance.aop.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class ExceptionMonitorPointcut extends AbstractPointcutAdvisor{

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(ExceptionMonitorPointcut.class);

	private Pointcut pointcut;

	private Advice advice;
	
	@Autowired
	private ExceptionMonitorInterceptor exceptionMonitorInterceptor;

	@PostConstruct
	public void init() {
		logger.info("init ExceptionMonitorPointcut........");
		this.pointcut = new AnnotationMatchingPointcut(null,RequestMapping.class);
		this.advice = exceptionMonitorInterceptor;
	}

	public Pointcut getPointcut() {
		return this.pointcut;
	}

	public Advice getAdvice() {
		return this.advice;
	}

}
