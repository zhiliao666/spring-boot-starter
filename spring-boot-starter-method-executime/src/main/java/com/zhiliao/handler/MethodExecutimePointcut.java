package com.zhiliao.handler;

import javax.annotation.PostConstruct;

import org.aopalliance.aop.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhiliao.annotation.Executime;
import com.zhiliao.config.MethodExecutimeProperties;

public class MethodExecutimePointcut extends AbstractPointcutAdvisor {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(MethodExecutimePointcut.class);

	private Pointcut pointcut;

	private Advice advice;

	@Autowired
	private MethodExecutimeProperties methodExecutimeProperties;
	

	@PostConstruct
	public void init() {
		logger.info("init MethodExecutimePointcut........");
		this.pointcut = new AnnotationMatchingPointcut(null,Executime.class);
		this.advice = new ExecutimeMethodInterceptor();
	}

	public Pointcut getPointcut() {
		return this.pointcut;
	}

	public Advice getAdvice() {
		return this.advice;
	}
}
