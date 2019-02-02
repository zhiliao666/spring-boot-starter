package com.zhiliao.tomcat.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zhiliao.tomcat.TomcatInfo;

/**
 * 日志实现指标打印
 *
 * @author zhangqh
 * @date 2019年2月2日
 */
public class LogStore  implements Store {

	private static final Logger logger = LoggerFactory.getLogger(LogStore.class);

	@Override
	public void storeInfo(TomcatInfo tomcatInfo) {
		StringBuilder builder = new StringBuilder();
		builder.append("maxThreads:").append(tomcatInfo.getMaxThreads())
				.append(",currentThreads:").append(tomcatInfo.getCurrentThreads())
				.append(",busyThreads:").append(tomcatInfo.getBusyThreads());
		logger.info("ServerInfo:" + builder.toString());
	}

}
