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
/**
 * 自定义方法性能拦截端点
 *
 * @author zhangqh
 * @date 2018年6月24日
 */
public class MethodExecutimePointcut extends AbstractPointcutAdvisor {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(MethodExecutimePointcut.class);

	private Pointcut pointcut;

	private Advice advice;

	@Autowired
	private MethodExecutimeProperties methodExecutimeProperties;
	

	@PostConstruct
	public void init() {
		/**
		 * 拦截方法上标注了Executime注解的所有方法
		 */
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
