package com.zhiliao.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
/**
 * 自定义tomcat容器创建工厂类
 *
 * @author zhangqh
 * @date 2019年2月2日
 */
@Component
public class ZhiliaoServerFactory extends TomcatWebServerFactoryCustomizer {

	public ZhiliaoServerFactory(Environment environment,
			ServerProperties serverProperties) {
		super(environment, serverProperties);
		
	}
	
	@Override
	public void customize(ConfigurableTomcatWebServerFactory factory) {
		super.customize(factory);
		factory.addConnectorCustomizers(new ZhiliaoTomcatConnectorCustomizer());
	}
	
}
