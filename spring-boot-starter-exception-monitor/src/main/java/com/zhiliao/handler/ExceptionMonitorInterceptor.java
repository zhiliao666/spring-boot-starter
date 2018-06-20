package com.zhiliao.handler;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhiliao.config.ExceptionMonitorProperties;
import com.zhiliao.until.ApiResult;

public class ExceptionMonitorInterceptor implements MethodInterceptor {

	private Logger logger = LoggerFactory.getLogger(ExceptionMonitorInterceptor.class);
	
	/**
	 * 自定义告警参数配置
	 */
	@Autowired
	private ExceptionMonitorProperties exceptionMonitorProperties;
	
	@Autowired
	private EmailMonitorExceptionProcess emailMonitorExceptionProcess;
	
	@Autowired
	private Environment env;
	
	/**
	 * mail对应参数配置
	 */
	@Autowired
	private MailProperties mailProperties;
	
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
    	
        Object result = null;
        try {
			// 一切正常的情况下，继续执行被拦截的方法
			result = invocation.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			// 自定义异常邮件通知
			result = handlerException(invocation, e);
		}
        return result;
    }
    
    private ApiResult<String> handlerException(MethodInvocation invocation, Throwable e) {
		// 获取是否开通异常邮件告警
		boolean emailState = exceptionMonitorProperties.getStatus();
		if (emailState) {
			processSendEmail(invocation, e);
		}
		return new ApiResult<String>().setError(ApiResult.errorCode500, "系统异常");
	}
    
    private void processSendEmail(MethodInvocation pjp, Throwable e) {

		// 获取被拦截的方法名
		String methodName = pjp.getMethod().getName();

		String failCode = e.getClass().getName();
		
		// 获取环境参数
		String envm = env.getProperty("spring.profiles.active");
		
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		String url = sra.getRequest().getRequestURL().toString();
		// 获取请求参数
		Object[] args = pjp.getArguments();
		String[] toEmail = exceptionMonitorProperties.getTo();
		StringBuffer msg = new StringBuffer();
		msg.append("请求url").append(url);
		msg.append("\n异常方法：").append(methodName);
		msg.append("\n请求参数：【").append(Arrays.asList(args));
		msg.append("】\n异常错误编码：").append(failCode);
		msg.append("\n错误详情：").append(e.getMessage());
		msg.append("\n堆栈信息").append(Arrays.asList(e.getStackTrace()));

		emailMonitorExceptionProcess.asyncSendEmailText("环境【" + envm
				+ "】异常 请求方法【" + methodName + "】参数【" + Arrays.asList(args)
				+ "】", msg.toString(), mailProperties.getUsername(),
				toEmail);
	}

}
