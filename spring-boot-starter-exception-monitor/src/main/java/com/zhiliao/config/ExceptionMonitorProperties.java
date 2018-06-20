package com.zhiliao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义异常告警配置类
 *
 * @author zhangqh
 * @date 2018年6月15日
 */
@ConfigurationProperties(prefix = "spring.exception.monitor")
public class ExceptionMonitorProperties {
	
	/**
	 * 默认true为开启 关闭告警邮件发送设为false
	 */
	private Boolean status = true;
	
	/**
	 * 接收告警邮件邮箱地址
	 */
	private String[] to;

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the to
	 */
	public String[] getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

}
