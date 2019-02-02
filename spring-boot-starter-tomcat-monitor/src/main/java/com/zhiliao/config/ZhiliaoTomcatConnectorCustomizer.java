package com.zhiliao.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;

import com.zhiliao.tomcat.TomcatMonitorLifecycleListener;
/**
 * 自定义tomcat容器扩展配置
 *
 * @author zhangqh
 * @date 2019年2月2日
 */
public class ZhiliaoTomcatConnectorCustomizer implements TomcatConnectorCustomizer   {

	@Override
	public void customize(Connector connector) {
		// 添加自定义的tomcat事件监听器
		connector.addLifecycleListener(new TomcatMonitorLifecycleListener());
	}

}
