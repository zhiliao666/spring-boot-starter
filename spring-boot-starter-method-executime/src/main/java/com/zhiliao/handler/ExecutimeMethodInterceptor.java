package com.zhiliao.handler;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecutimeMethodInterceptor implements MethodInterceptor {

	private Logger logger = LoggerFactory.getLogger(ExecutimeMethodInterceptor.class);
	
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();
        logger.info("====method ({}), execution time ({}) ", methodName, (end - start));
        return result;
    }

}
