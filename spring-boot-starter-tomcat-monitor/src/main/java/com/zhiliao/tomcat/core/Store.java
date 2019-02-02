package com.zhiliao.tomcat.core;

import com.zhiliao.tomcat.TomcatInfo;

/**
 * 指标store接口
 *
 * @author zhangqh
 * @date 2019年2月2日
 */
public interface Store {
	public void storeInfo(TomcatInfo tomcatInfo);
}
