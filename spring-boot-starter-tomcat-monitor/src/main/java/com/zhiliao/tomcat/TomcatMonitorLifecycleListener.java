package com.zhiliao.tomcat;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

/**
 * 自定义tomcat事件监听
 *
 * @author zhangqh
 * @date 2019年2月2日
 */
public class TomcatMonitorLifecycleListener implements LifecycleListener {

	private TomcatInfoCollection collect = new TomcatInfoCollection();
	 
    @Override
    public void lifecycleEvent(LifecycleEvent event ) {
//        Lifecycle lifecycle = event .getLifecycle();
         if (Lifecycle.AFTER_START_EVENT .equals(event .getType())) {
             collect.startMonitor();
        }
         if (Lifecycle.BEFORE_STOP_EVENT .equals(event .getType())) {
             collect.stopMonitor();
        }
   }


}
